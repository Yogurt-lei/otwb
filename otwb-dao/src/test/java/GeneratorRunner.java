import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * GeneratorRunner
 *
 * @author <a href="yogurt_lei@foxmail.com">Yogurt_lei</a>
 * @version v1.0 , 2018-04-22 15:30
 */
@Slf4j
public class GeneratorRunner {
    private File configFile;

    @Before
    public void before() {
        configFile = new File("F:\\IDEA_WorkSpace\\otwb\\otwb-dao\\src\\test\\resources\\generator\\generatorConfig.xml");
    }

    @Test
    public void generator() throws Exception {
        List<String> warnings = new ArrayList<>();
        boolean overwrite = true; // 是否覆盖
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = cp.parseConfiguration(configFile);
        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
        MyBatisGenerator myBatisGenerator = new  MyBatisGenerator(config, callback, warnings);
        myBatisGenerator.generate(null);
        for (String warning : warnings) {
            log.warn(warning);
        }
    }
}
