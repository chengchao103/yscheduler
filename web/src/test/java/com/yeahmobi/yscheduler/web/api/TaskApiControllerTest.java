package com.yeahmobi.yscheduler.web.api;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import com.alibaba.fastjson.JSON;
import com.yeahmobi.yscheduler.model.Task;
import com.yeahmobi.yscheduler.model.service.TaskService;
import com.yeahmobi.yscheduler.model.type.DependingStatus;
import com.yeahmobi.yscheduler.model.type.TaskStatus;
import com.yeahmobi.yscheduler.model.type.TaskType;
import com.yeahmobi.yscheduler.web.common.HttpServletRequestMapWrapper;
import com.yeahmobi.yunit.DbUnitTestExecutionListener;
import com.yeahmobi.yunit.annotation.DatabaseSetup;

/**
 * Leo Liang
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:api/applicationContext-test.xml" })
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, DbUnitTestExecutionListener.class })
public class TaskApiControllerTest extends ApiControllerBaseTest {

    @Autowired
    private TaskApiController taskApiController;

    @Autowired
    private TaskService       taskService;

    @Test
    @DatabaseSetup
    public void testNameExists() throws Exception {
        HttpServletRequestMapWrapper request = createRequest();
        addCommonReqParam(request);

        Map<String, Object> expectedReturnValue = new HashMap<String, Object>();
        expectedReturnValue.put("exists", true);
        assertResponse(ApiStatusCode.SUCCESS, null, expectedReturnValue,
                       this.taskApiController.nameExists(request, "test1"));
    }

    @Test
    @DatabaseSetup
    public void testNameExistsWithNotExists() throws Exception {
        HttpServletRequestMapWrapper request = createRequest();
        addCommonReqParam(request);

        Map<String, Object> expectedReturnValue = new HashMap<String, Object>();
        expectedReturnValue.put("exists", false);
        assertResponse(ApiStatusCode.SUCCESS, null, expectedReturnValue,
                       this.taskApiController.nameExists(request, "test2000"));
    }

    @Test
    @DatabaseSetup
    public void testNameExistsWithNotTaskName() throws Exception {
        HttpServletRequestMapWrapper request = createRequest();
        addCommonReqParam(request);

        assertResponse(ApiStatusCode.BIZ_ERROR, "Task name must not be null or empty", null,
                       this.taskApiController.nameExists(request, ""));
    }

    @Test
    @DatabaseSetup
    public void testDisableSchedule() throws Exception {
        HttpServletRequestMapWrapper request = createRequest();
        addCommonReqParam(request);

        assertResponse(ApiStatusCode.SUCCESS, null, null,
                       this.taskApiController.disableSchedule(request, "test1", "admin1", "token1"));
        Assert.assertTrue(TaskStatus.PAUSED.equals(this.taskService.get(1).getStatus()));
    }

    @Test
    @DatabaseSetup
    public void testDisableScheduleWithoutUserName() throws Exception {
        HttpServletRequestMapWrapper request = createRequest();
        addCommonReqParam(request);

        assertResponse(ApiStatusCode.BIZ_ERROR, "User name must not be null or empty", null,
                       this.taskApiController.disableSchedule(request, "test1", "", "token1"));
        Assert.assertTrue(TaskStatus.OPEN.equals(this.taskService.get(1).getStatus()));
    }

    @Test
    @DatabaseSetup
    public void testDisableScheduleWithUnexistsUser() throws Exception {
        HttpServletRequestMapWrapper request = createRequest();
        addCommonReqParam(request);

        assertResponse(ApiStatusCode.BIZ_ERROR, "User(fdfds) does not exist", null,
                       this.taskApiController.disableSchedule(request, "test1", "fdfds", "token1"));
        Assert.assertTrue(TaskStatus.OPEN.equals(this.taskService.get(1).getStatus()));
    }

    @Test
    @DatabaseSetup
    public void testDisableScheduleWithoutUserToken() throws Exception {
        HttpServletRequestMapWrapper request = createRequest();
        addCommonReqParam(request);

        assertResponse(ApiStatusCode.BIZ_ERROR, "User token must not be null or empty", null,
                       this.taskApiController.disableSchedule(request, "test1", "admin1", ""));
        Assert.assertTrue(TaskStatus.OPEN.equals(this.taskService.get(1).getStatus()));
    }

    @Test
    @DatabaseSetup
    public void testDisableScheduleWithWrongUserToken() throws Exception {
        HttpServletRequestMapWrapper request = createRequest();
        addCommonReqParam(request);

        assertResponse(ApiStatusCode.BIZ_ERROR, "User token(token2) error", null,
                       this.taskApiController.disableSchedule(request, "test1", "admin1", "token2"));
        Assert.assertTrue(TaskStatus.OPEN.equals(this.taskService.get(1).getStatus()));
    }

    @Test
    @DatabaseSetup
    public void testDisableScheduleWithoutTaskName() throws Exception {
        HttpServletRequestMapWrapper request = createRequest();
        addCommonReqParam(request);

        assertResponse(ApiStatusCode.BIZ_ERROR, "Task name must not be null or empty", null,
                       this.taskApiController.disableSchedule(request, "", "admin1", "token1"));
        Assert.assertTrue(TaskStatus.OPEN.equals(this.taskService.get(1).getStatus()));
    }

    @Test
    @DatabaseSetup
    public void testDisableScheduleWithUnexistsTaskName() throws Exception {
        HttpServletRequestMapWrapper request = createRequest();
        addCommonReqParam(request);

        assertResponse(ApiStatusCode.BIZ_ERROR, "Task(test2000) does not exist", null,
                       this.taskApiController.disableSchedule(request, "test2000", "admin1", "token1"));
        Assert.assertTrue(TaskStatus.OPEN.equals(this.taskService.get(1).getStatus()));
    }

    @Test
    @DatabaseSetup
    public void testDisableScheduleWithoutModifyAuth() throws Exception {
        HttpServletRequestMapWrapper request = createRequest();
        addCommonReqParam(request);

        assertResponse(ApiStatusCode.BIZ_ERROR, "User(admin2) can not modify task(test1)", null,
                       this.taskApiController.disableSchedule(request, "test1", "admin2", "token2"));
        Assert.assertTrue(TaskStatus.OPEN.equals(this.taskService.get(1).getStatus()));
    }

    @Test
    @DatabaseSetup
    public void testEnableSchedule() throws Exception {
        HttpServletRequestMapWrapper request = createRequest();
        addCommonReqParam(request);

        assertResponse(ApiStatusCode.SUCCESS, null, null,
                       this.taskApiController.enableSchedule(request, "test2", "admin1", "token1"));
        Assert.assertTrue(TaskStatus.OPEN.equals(this.taskService.get(2).getStatus()));
    }

    @Test
    @DatabaseSetup
    public void testCreateShellTask() throws Exception {
        HttpServletRequestMapWrapper request = createRequest();
        addCommonReqParam(request);

        request.put("type", "1");
        request.put("crontab", "0 0 0 * * ?");
        request.put("command", "echo 1");
        request.put("agent", "platform_1");
        request.put("timeout", "100");
        request.put("retryTimes", "2");
        request.put("description", "desc");
        request.put("canSkip", "false");
        request.put("concurrent", "false");
        request.put("condition", "completed");

        ApiResponse response = JSON.parseObject(this.taskApiController.create(request, "testCreateShell", "admin1",
                                                                              "token1"), ApiResponse.class);

        int id = (Integer) response.getReturnValue().get("id");
        Date now = new Date();
        assertTask(this.taskService.get(id), id, 1L, "testCreateShell", "echo 1", now, now, "0 0 0 * * ?", "desc", now,
                   1L, 2, TaskStatus.OPEN, 100, TaskType.SHELL, false, DependingStatus.COMPLETED);
    }

    @Test
    @DatabaseSetup
    public void testCreateHttpTask() throws Exception {
        HttpServletRequestMapWrapper request = createRequest();
        addCommonReqParam(request);

        request.put("type", "20");
        request.put("crontab", "0 0 0 * * ?");
        request.put("calloutUrl", "http://test.com/callout");
        request.put("cancelUrl", "http://test.com/cancel");
        request.put("needCallback", "true");
        request.put("timeout", "100");
        request.put("retryTimes", "2");
        request.put("description", "desc");
        request.put("canSkip", "false");
        request.put("concurrent", "false");
        request.put("condition", "success");

        ApiResponse response = JSON.parseObject(this.taskApiController.create(request, "testCreateHttp", "admin1",
                                                                              "token1"), ApiResponse.class);

        int id = (Integer) response.getReturnValue().get("id");

        Date now = new Date();
        assertTask(this.taskService.get(id), id, 1L, "testCreateHttp",
                   "http://test.com/callout;true;http://test.com/cancel", now, now, "0 0 0 * * ?", "desc", now, 1L, 2,
                   TaskStatus.OPEN, 100, TaskType.HTTP, false, DependingStatus.SUCCESS);
    }

    @Test
    @DatabaseSetup
    public void testCreateHttpTaskWithoutPlatformAgent() throws Exception {
        HttpServletRequestMapWrapper request = createRequest();
        addCommonReqParam(request);

        request.put("type", "20");
        request.put("crontab", "0 0 0 * * ?");
        request.put("calloutUrl", "http://test.com/callout");
        request.put("cancelUrl", "http://test.com/cancel");
        request.put("needCallback", "true");
        request.put("timeout", "100");
        request.put("retryTimes", "2");
        request.put("description", "desc");

        assertResponse(ApiStatusCode.BIZ_ERROR, "There is no platform agent for create an HTTP type task", null,
                       this.taskApiController.create(request, "testCreateHttp", "admin1", "token1"));

    }

    @Test
    @DatabaseSetup
    public void testCreateHttpTaskWithoutCancelUrl() throws Exception {
        HttpServletRequestMapWrapper request = createRequest();
        addCommonReqParam(request);

        request.put("type", "20");
        request.put("crontab", "0 0 0 * * ?");
        request.put("calloutUrl", "http://test.com/callout");
        request.put("cancelUrl", "  ");
        request.put("needCallback", "true");
        request.put("timeout", "100");
        request.put("retryTimes", "2");
        request.put("description", "desc");
        request.put("canSkip", "false");

        ApiResponse response = JSON.parseObject(this.taskApiController.create(request, "testCreateHttp", "admin1",
                                                                              "token1"), ApiResponse.class);

        int id = (Integer) response.getReturnValue().get("id");

        Date now = new Date();
        assertTask(this.taskService.get(id), id, 1L, "testCreateHttp", "http://test.com/callout;true;", now, now,
                   "0 0 0 * * ?", "desc", now, 1L, 2, TaskStatus.OPEN, 100, TaskType.HTTP, false, DependingStatus.NONE);
    }

    @Test
    @DatabaseSetup
    public void testCreateHttpTaskRetryTimesAndTimeoutDefault() throws Exception {
        HttpServletRequestMapWrapper request = createRequest();
        addCommonReqParam(request);

        request.put("type", "20");
        request.put("crontab", "0 0 0 * * ?");
        request.put("calloutUrl", "http://test.com/callout");
        request.put("cancelUrl", "http://test.com/cancel");
        request.put("needCallback", "true");
        request.put("timeout", "-1");
        request.put("retryTimes", "-2");
        request.put("description", "desc");
        request.put("canSkip", "true");

        ApiResponse response = JSON.parseObject(this.taskApiController.create(request, "testCreateHttp", "admin1",
                                                                              "token1"), ApiResponse.class);

        int id = (Integer) response.getReturnValue().get("id");

        Date now = new Date();
        assertTask(this.taskService.get(id), id, 1L, "testCreateHttp",
                   "http://test.com/callout;true;http://test.com/cancel", now, now, "0 0 0 * * ?", "desc", now, 1L, 0,
                   TaskStatus.OPEN, 0, TaskType.HTTP, true, DependingStatus.NONE);
    }

    @Test
    @DatabaseSetup
    public void testCreateTaskWithoutUser() throws Exception {
        HttpServletRequestMapWrapper request = createRequest();
        addCommonReqParam(request);

        request.put("type", "20");
        request.put("crontab", "0 0 0 * * ?");
        request.put("calloutUrl", "http://test.com/callout");
        request.put("cancelUrl", "http://test.com/cancel");
        request.put("needCallback", "true");
        request.put("timeout", "100");
        request.put("retryTimes", "2");
        request.put("description", "desc");

        assertResponse(ApiStatusCode.BIZ_ERROR, "User name must not be null or empty", null,
                       this.taskApiController.create(request, "testCreateHttp", "", "token1"));
    }

    @Test
    @DatabaseSetup
    public void testCreateTaskWithoutType() throws Exception {
        HttpServletRequestMapWrapper request = createRequest();
        addCommonReqParam(request);

        request.put("crontab", "0 0 0 * * ?");
        request.put("calloutUrl", "http://test.com/callout");
        request.put("cancelUrl", "http://test.com/cancel");
        request.put("needCallback", "true");
        request.put("timeout", "100");
        request.put("retryTimes", "2");
        request.put("description", "desc");

        assertResponse(ApiStatusCode.BIZ_ERROR, "Task type must not be null or empty and must be numeric", null,
                       this.taskApiController.create(request, "testCreateHttp", "admin1", "token1"));
    }

    @Test
    @DatabaseSetup
    public void testCreateTaskWithTypeStr() throws Exception {
        HttpServletRequestMapWrapper request = createRequest();
        addCommonReqParam(request);

        request.put("type", "jfoiw");
        request.put("crontab", "0 0 0 * * ?");
        request.put("calloutUrl", "http://test.com/callout");
        request.put("cancelUrl", "http://test.com/cancel");
        request.put("needCallback", "true");
        request.put("timeout", "100");
        request.put("retryTimes", "2");
        request.put("description", "desc");

        assertResponse(ApiStatusCode.BIZ_ERROR, "Task type must not be null or empty and must be numeric", null,
                       this.taskApiController.create(request, "testCreateHttp", "admin1", "token1"));
    }

    @Test
    @DatabaseSetup
    public void testCreateTaskWithUnsupportType() throws Exception {
        HttpServletRequestMapWrapper request = createRequest();
        addCommonReqParam(request);

        request.put("type", "100");
        request.put("crontab", "0 0 0 * * ?");
        request.put("calloutUrl", "http://test.com/callout");
        request.put("cancelUrl", "http://test.com/cancel");
        request.put("needCallback", "true");
        request.put("timeout", "100");
        request.put("retryTimes", "2");
        request.put("description", "desc");

        assertResponse(ApiStatusCode.BIZ_ERROR, "Task type error", null,
                       this.taskApiController.create(request, "testCreateHttp", "admin1", "token1"));
    }

    @Test
    @DatabaseSetup
    public void testCreateTaskWithoutCrontab() throws Exception {
        HttpServletRequestMapWrapper request = createRequest();
        addCommonReqParam(request);

        request.put("type", "1");
        request.put("calloutUrl", "http://test.com/callout");
        request.put("cancelUrl", "http://test.com/cancel");
        request.put("needCallback", "true");
        request.put("timeout", "100");
        request.put("retryTimes", "2");
        request.put("description", "desc");

        assertResponse(ApiStatusCode.BIZ_ERROR, "Task crontab must not be null or empty", null,
                       this.taskApiController.create(request, "testCreateHttp", "admin1", "token1"));
    }

    @Test
    @DatabaseSetup
    public void testCreateTaskWithWrongCrontab() throws Exception {
        HttpServletRequestMapWrapper request = createRequest();
        addCommonReqParam(request);

        request.put("type", "1");
        request.put("crontab", "0 0 0 a * * ?");
        request.put("calloutUrl", "http://test.com/callout");
        request.put("cancelUrl", "http://test.com/cancel");
        request.put("needCallback", "true");
        request.put("timeout", "100");
        request.put("retryTimes", "2");
        request.put("description", "desc");

        assertResponse(ApiStatusCode.BIZ_ERROR,
                       "错误的调度时间表达式:\nCron expression must consist of 6 fields (found 7 in \"0 0 0 a * * ?\")", null,
                       this.taskApiController.create(request, "testCreateHttp", "admin1", "token1"));
    }

    @Test
    @DatabaseSetup
    public void testCreateTaskWithWrongNeedCallback() throws Exception {
        HttpServletRequestMapWrapper request = createRequest();
        addCommonReqParam(request);

        request.put("type", "20");
        request.put("crontab", "0 0 0 * * ?");
        request.put("calloutUrl", "http://test.com/callout");
        request.put("cancelUrl", "http://test.com/cancel");
        request.put("timeout", "100");
        request.put("needCallback", "sss");
        request.put("retryTimes", "2");
        request.put("description", "desc");

        assertResponse(ApiStatusCode.BIZ_ERROR, "needCallback must not be null or empty and must be true or false",
                       null, this.taskApiController.create(request, "testCreateHttp", "admin1", "token1"));
    }

    @Test
    @DatabaseSetup
    public void testCreateTaskWithoutNeedCallback() throws Exception {
        HttpServletRequestMapWrapper request = createRequest();
        addCommonReqParam(request);

        request.put("type", "20");
        request.put("crontab", "0 0 0 * * ?");
        request.put("calloutUrl", "http://test.com/callout");
        request.put("cancelUrl", "http://test.com/cancel");
        request.put("timeout", "100");
        request.put("retryTimes", "2");
        request.put("description", "desc");

        assertResponse(ApiStatusCode.BIZ_ERROR, "needCallback must not be null or empty and must be true or false",
                       null, this.taskApiController.create(request, "testCreateHttp", "admin1", "token1"));
    }

    @Test
    @DatabaseSetup
    public void testCreateTaskWithoutCalloutUrl() throws Exception {
        HttpServletRequestMapWrapper request = createRequest();
        addCommonReqParam(request);

        request.put("type", "20");
        request.put("crontab", "0 0 0 * * ?");
        request.put("cancelUrl", "http://test.com/cancel");
        request.put("timeout", "100");
        request.put("needCallback", "true");
        request.put("retryTimes", "2");
        request.put("description", "desc");

        assertResponse(ApiStatusCode.BIZ_ERROR, "CalloutUrl must not be null or empty", null,
                       this.taskApiController.create(request, "testCreateHttp", "admin1", "token1"));
    }

    @Test
    @DatabaseSetup
    public void testCreateShellTaskWithUnexistsAgent() throws Exception {
        HttpServletRequestMapWrapper request = createRequest();
        addCommonReqParam(request);

        request.put("type", "1");
        request.put("crontab", "0 0 0 * * ?");
        request.put("command", "echo 1");
        request.put("timeout", "100");
        request.put("retryTimes", "2");
        request.put("description", "desc");
        request.put("agent", "1231");

        assertResponse(ApiStatusCode.BIZ_ERROR, "Agent(1231) not found", null,
                       this.taskApiController.create(request, "testCreateHttp", "admin1", "token1"));
    }

    @Test
    @DatabaseSetup
    public void testCreateShellTaskWithoutAgent() throws Exception {
        HttpServletRequestMapWrapper request = createRequest();
        addCommonReqParam(request);

        request.put("type", "1");
        request.put("crontab", "0 0 0 * * ?");
        request.put("command", "echo 1");
        request.put("timeout", "100");
        request.put("retryTimes", "2");
        request.put("description", "desc");

        assertResponse(ApiStatusCode.BIZ_ERROR, "Agent id must not be null or empty and must be numeric", null,
                       this.taskApiController.create(request, "testCreateHttp", "admin1", "token1"));
    }

    @Test
    @DatabaseSetup
    public void testCreateShellTaskWithoutCommand() throws Exception {
        HttpServletRequestMapWrapper request = createRequest();
        addCommonReqParam(request);

        request.put("type", "1");
        request.put("crontab", "0 0 0 * * ?");
        request.put("timeout", "100");
        request.put("retryTimes", "2");
        request.put("description", "desc");
        request.put("agent", "platform_1");

        assertResponse(ApiStatusCode.BIZ_ERROR, "Command must not be null or empty", null,
                       this.taskApiController.create(request, "testCreateHttp", "admin1", "token1"));
    }

    private void assertTask(Task task, long id, long agentId, String name, String command, Date createTime,
                            Date updateTime, String crontab, String desc, Date lastScheduleTime, long owner,
                            Integer retryTimes, TaskStatus status, Integer timeout, TaskType type, boolean canSkip,
                            DependingStatus dependingStatus) {
        Assert.assertEquals(Long.valueOf(id), task.getId());
        Assert.assertEquals(Long.valueOf(agentId), task.getAgentId());
        Assert.assertEquals(command, task.getCommand());
        TestUtils.generallyEquals(createTime, task.getCreateTime());
        TestUtils.generallyEquals(updateTime, task.getUpdateTime());
        Assert.assertEquals(crontab, task.getCrontab());
        Assert.assertEquals(desc, task.getDescription());
        if (lastScheduleTime == null) {
            Assert.assertNull(task.getLastScheduleTime());
        } else {
            TestUtils.generallyEquals(lastScheduleTime, task.getLastScheduleTime());
        }
        Assert.assertEquals(name, task.getName());
        Assert.assertEquals(Long.valueOf(owner), task.getOwner());
        Assert.assertEquals(retryTimes, task.getRetryTimes());
        Assert.assertEquals(status, task.getStatus());
        Assert.assertEquals(timeout, task.getTimeout());
        Assert.assertEquals(type, task.getType());
        Assert.assertEquals(Boolean.valueOf(canSkip), task.getCanSkip());
        Assert.assertEquals(dependingStatus, task.getLastStatusDependency());
    }
}
