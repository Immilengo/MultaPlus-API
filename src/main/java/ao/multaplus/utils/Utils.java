package ao.multaplus.utils;
import java.util.Random;

public class Utils {
    static public Integer generateOTP(){
        Random random = new Random();
        return random.nextInt(999999);
    }
}
