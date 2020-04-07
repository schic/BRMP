package com.wondersgroup.empi.util.common;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:/applicationContext/applicationContext-commons.xml",  
"classpath*:/applicationContext/applicationContext-cxf.xml" })
@Transactional
public class BaseJunit {

}
