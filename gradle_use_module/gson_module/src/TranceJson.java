import com.google.gson.Gson;
import java.lang.reflect.Type;

public class TranceJson {
    private final Gson gson;

    public TranceJson() {
        this.gson = new Gson();
    }

    // 메서드명 유지
    public String toString(Object obj) {
        return this.gson.toJson(obj);
    }

    // 메서드명 유지
    public Object toObject(String str, Type type) {
        return this.gson.fromJson(str, type);
    }
}