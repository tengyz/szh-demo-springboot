package com.gientech.stms.test;

import com.gientech.stms.application.test.OrderServiceImplTest;
import org.junit.platform.suite.api.IncludeTags;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@IncludeTags("junit_order")
@SelectClasses({OrderServiceImplTest.class})
public class StartDemoApplicationTests {

}
