package com.testingbot;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import com.testingbot.Parallelized;

@RunWith(Parallelized.class)
public class TestingBotJUnitTest {
    public WebDriver driver;

    private static JSONObject config;

    @Parameter(value = 0)
    public int taskID;

    @Parameters
    public static Iterable<? extends Object> data() throws Exception {
        List<Integer> taskIDs = new ArrayList<Integer>();

        if (System.getProperty("config") != null) {
            JSONParser parser = new JSONParser();
            config = (JSONObject) parser.parse(new FileReader("src/test/resources/conf/" + System.getProperty("config")));
            int envs = ((JSONArray)config.get("environments")).size();

            for(int i=0; i<envs; i++) {
                taskIDs.add(i);
            }
        }

        return taskIDs;
    }

    @Before
    public void setUp() throws Exception {
        JSONArray envs = (JSONArray) config.get("environments");

        DesiredCapabilities capabilities = new DesiredCapabilities();

        Map<String, String> envCapabilities = (Map<String, String>) envs.get(taskID);
        Iterator it = envCapabilities.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            capabilities.setCapability(pair.getKey().toString(), pair.getValue().toString());
        }
        
        Map<String, String> commonCapabilities = (Map<String, String>) config.get("capabilities");
        it = commonCapabilities.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            if(capabilities.getCapability(pair.getKey().toString()) == null){
                capabilities.setCapability(pair.getKey().toString(), pair.getValue().toString());
            }
        }

        String key = System.getenv("TB_KEY");
        if (key == null) {
            key = (String) config.get("key");
        }

        String secret = System.getenv("TB_SECRET");
        if (secret == null) {
            secret = (String) config.get("secret");
        }

        driver = new RemoteWebDriver(new URL("http://"+key+":"+secret+"@"+config.get("server")+"/wd/hub"), capabilities);
    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
    }
}
