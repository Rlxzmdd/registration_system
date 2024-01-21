import com.javaweb.admin.AdminApplication;
import com.javaweb.generator.utils.CodeGenerateUtils;
import com.javaweb.generator.utils.SpringUtil;
import com.javaweb.system.mapper.MenuMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AdminApplication.class)
public class CodeGenerateUtilStart extends BaseTest {
    @Override
    public void init() {
        System.out.println("开始执行代码生成-----------------");
    }

    @Override
    public void after() {
        System.out.println("代码生成结束-----------------");
    }

    @Test
    public void generateStart() throws Exception {
        ApplicationContext context = SpringUtil.getApplicationContext();
        CodeGenerateUtils codeGenerateUtils = new CodeGenerateUtils(context.getBean(MenuMapper.class));
        codeGenerateUtils.setAuthor("Cheney");
        /*设置表前缀，将会自动删除*/
        codeGenerateUtils.setTablePredix("wechat_");
        /*设置项目根目录*/
        codeGenerateUtils.setProjectPath("/Users/Cheney/Documents/Coding/registration_system/JavaWeb/");
        /*设置vue一级路径*/
        codeGenerateUtils.setView1Level("registration");
        /*设置vue二级路径*/
        codeGenerateUtils.setView2Level("wechat_route");
        /*设置生成目标所在包名*/
        codeGenerateUtils.setSbPackName("com.withmore.user");
        /*设置sb子模块名*/
        codeGenerateUtils.setSubModuleName("wechat");
        /*生成目标sql所在database名*/
        codeGenerateUtils.setDatabaseName("registration_system");
        /*生成目标sql表名*/
        codeGenerateUtils.setTableName("wechat_route");
        /*表注释 ，导航栏标题*/
        codeGenerateUtils.setTableAnnotation("微信小程序路由表");
        /*生成*/
        codeGenerateUtils.generateFile();
    }
}
