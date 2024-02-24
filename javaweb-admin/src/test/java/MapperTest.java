import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.javaweb.admin.AdminApplication;
import com.withmore.event.form.entity.FormAudit;
import com.withmore.event.form.entity.FormAuditNotice;
import com.withmore.event.form.entity.FormItem;
import com.withmore.event.form.mapper.FormAuditMapper;
import com.withmore.event.form.mapper.FormAuditNoticeMapper;
import com.withmore.event.form.mapper.FormItemMapper;
import com.withmore.user.student.entity.Student;
import com.withmore.user.student.mapper.StudentMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.ArrayList;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = AdminApplication.class)
public class MapperTest extends BaseTest {

    @Autowired
    private FormAuditNoticeMapper formAuditNoticeMapper;

    @Autowired
    private FormItemMapper formItemMapper;

    @Autowired
    private FormAuditMapper formAuditMapper;

    @Autowired
    private StudentMapper studentMapper;

    /**
     * 清除重复通知
     */
    @Test
    public void dropRepeat() {
        QueryWrapper<FormItem> formItemMapperQueryWrapper = new QueryWrapper<>();
        formItemMapperQueryWrapper.eq("user_type", "student");
        formItemMapperQueryWrapper.eq("mark", 1);
        List<FormItem> items = formItemMapper.selectList(formItemMapperQueryWrapper);
        for (FormItem item :
                items) {
            String uuid = item.getItemUuid();
            QueryWrapper<FormAuditNotice> formAuditNoticeQueryWrapper = new QueryWrapper<>();
            formAuditNoticeQueryWrapper.eq("item_uuid", uuid);
            formAuditNoticeQueryWrapper.eq("mark", 1);
            List<FormAuditNotice> notices = formAuditNoticeMapper.selectList(formAuditNoticeQueryWrapper);

            List<Integer> ids = new ArrayList<>();
            for (FormAuditNotice notice : notices) {
                if (notice.getAuditId() != null) {
                    ids.add(notice.getAuditId());
                }
            }

            /*
             * 有一个审核通过的通知，删掉其他所有通知
             * 没有任何一个审核过的通知 或有一个审核或多个审核失败的通知，删掉除了最后一个通知
             */

            List<FormAudit> audits = new ArrayList<>();

            if (ids.size() != 0) {
                QueryWrapper<FormAudit> formAuditQueryWrapper = new QueryWrapper<>();
                formAuditQueryWrapper.eq("mark", 1);
                formAuditQueryWrapper.in("id", ids);
                audits = formAuditMapper.selectList(formAuditQueryWrapper);
            }

            boolean passed = false;
            for (FormAudit audit : audits) {
                if (audit.getIsThrough()) {
                    passed = true;
                    break;
                }
            }

            if (passed) {
                for (FormAuditNotice notice : notices) {
                    if (notice.getAuditId() == null) {
                        formAuditNoticeMapper.deleteById(notice);
                    }
                }
            } else {
                for (int i = 0; i < notices.size() - 1; i++) {
                    FormAuditNotice notice = notices.get(i);
                    formAuditNoticeMapper.deleteById(notice);
                }
            }
        }
    }

    @Test
    public void addAuditNotice(){
        String formKey= "zo2I8y5H1lq";
        QueryWrapper<FormItem> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("mark" , 1);
        queryWrapper.eq( "form_key" , formKey);
        List<FormItem> items = formItemMapper.selectList(queryWrapper);
        for (FormItem item : items) {
            String submitNumber = item.getSubmitNumber();
            QueryWrapper<Student> studentQueryWrapper = new QueryWrapper<>();
            studentQueryWrapper.eq("mark" ,1 );
            studentQueryWrapper.eq("stu_number" , submitNumber);
            Student student = studentMapper.selectOne(studentQueryWrapper);
            if (student.getClassesId().equals(893)){
                FormAuditNotice notice = new FormAuditNotice();
                notice.setFormKey(formKey);
                notice.setItemUuid(item.getItemUuid());
                notice.setReviewerNumber("20680364");
                notice.setReviewerType("student");
                formAuditNoticeMapper.insert(notice);
            }

        }
    }

}
