# 概述

任务（Task）是用户可以配置的最小单位，任务通常包括一系列的配置。

# 基本概念

| 概念    | 说明            | 
|:------------: |:----------------:|
| 类型    | shell或http|
| 执行方式 | 用户可以选择一台agent执行自己的任务<br />也可以由调度系统在用户组的所有机器中挑选一台执行   |
| 上传程序  | 用户可以上传zip或sh程序，在需要执行的时候，这个程序文件会被下载到需要的agent上 |
| 名称 | 任务名必须唯一且不可更改|
| 命令 | 任务在执行的时候实际触发的shell指令，可以填多条，用分号隔开|
| 调度表达式 | 使用quartz表达式，兼容了linux下的crontab表达式。平台支持分钟级别的调度。|
| 执行超时时间 | 任务执行超过这个时间，用户会收到报警，但并会影响任务继续执行|
| 重试次数 | 重试次数为n，则任务最多可能会被执行n+1次|
| 允许跳过 | 配置为允许时，同一任务的两次调度，时间上如果出现重叠，后一次调度会被跳过。|
| 允许并行 | 配置为允许时，同一任务的两次调度，时间上如果出现重叠，后一次会立刻运行,与前一次调度并行。|
| 触发条件 | 完成即触发，后一次调度在前一次完成的情况下触发<br/>成功才触发，后一次调度在前一次成功的情况下触发|

#实例状态
| 状态    | 说明            | 
|:------------: |:----------------:|
| 检查依赖    | 该任务在等待另一个任务完成|
| 待运行 | 任务依赖被满足，即将被执行 |
| 运行中 | 任务正在执行中 |
| 运行成功 | 对于shell任务，shell命令的返回值为0 |
| 运行失败 | 对于shell任务，shell命令的返回值非0 |
| 工作流失败 | 在私有工作流中一个任务执行失败，则所有的未运行的任务会被置为工作流失败 |
| 取消运行 | 用户取消了任务的运行 |
| 被跳过 | 任务若配置了允许跳过（默认配置），任务的到达了预定的调度时间，如果此时前一次调度未完成，则任务被直接跳过 |
| 未知的结束状态 | 对于shell任务，shell命令的返回值非0 |

#分类
##shell任务
在远程主机上执行shell命令
##http任务
执行任务的机器上不需要部署 agent，而是提供 http 接口，yscheduler 将通过 call http 接口的方式来调度您的任务。 
###1. 关于 “调用url” 
* “调用url”由您在创建任务时填写，yscheduler将通过调用 url 来触发您的任务，调用时会带上 “txId 参数” (如果勾选了回调还会带上 “callback 参数”)。  
调用后，若您的接口返回 200，则表示调用成功(注意只是调用成功，不是任务成功，任务成功与否，需要视下方的“回调”而定)。 
例如您可以填写：`http://\<your path>/run`，则 yscheduler 会调用`http://\<your path>?txId=xx&callback=xxx` 来触发您的任务。   
注意： yscheduler 通过 url 触发您的任务后，您的任务的运行时间如果比较长的话 (比如超过 10 秒)，则建议您异步执行任务，否则 http call 将超时。

###2. 关于 “是否需要回调” 
* 在创建任务时，您可以设置 “是否需要回调”，表示您是否需要回调 yscheduler 来设置任务的运行结果 (returnValue) 和日志 (log)。  
* 如果需要回调，则触发您的任务时，会带上 callback 给您，您的任务执行完之后，需要回调该callback，并且添加 returnValue 的参数，表示是否执行成功 (0表示成功，非0表示失败)，同时可以通过 log 参数 (post 方式) 回传 log。 
例如：`callback=http://<agent address>/yscheduler/?eventType=TASK_CALLBACK&txId=92&returnValue=` 
您的任务完成后，需要调用 `http://<agent address>/yscheduler/?eventType=TASK_CALLBACK&txId=92&returnValue=0或1` 来告诉 yscheduler 运行结果，另外可加上 log 参数上传日志，以便在 yscheduler 界面查看。   
* 如果不需要回调，则触发您的 url 之后，任务即结束，不需要等待回调。您的url如果返回200，表示任务成功，否则任务失败。

###3. 关于 “取消url” 
* 若您的任务支持取消，则需要填写 “取消 url”。当您在 yscheduler 系统界面上，点击取消任务时，yscheduler 会调用该 url 通知您。 
例如您可以填写：`http://<your path>/cancel`，则 yscheduler 会在取消任务前，调用 `http://<your path>/cancel?txId=xx` 来通知您的任务。  
* 如果不需要取消，则可以不填该 url
