import com.itzpy.utils.StringTools;
import org.apache.commons.codec.digest.DigestUtils;

public class PasswordTest {
    public static void main(String[] args) {
        String password = "123456qa";
        String encodedPassword = StringTools.encodeMd5(password);
        String directMd5 = DigestUtils.md5Hex(password);
        
        System.out.println("原始密码: " + password);
        System.out.println("StringTools.encodeMd5结果: " + encodedPassword);
        System.out.println("DigestUtils.md5Hex结果: " + directMd5);
        System.out.println("两者是否相等: " + encodedPassword.equals(directMd5));
    }
}