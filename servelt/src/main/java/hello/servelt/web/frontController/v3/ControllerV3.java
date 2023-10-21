package hello.servelt.web.frontController.v3;

import hello.servelt.web.frontController.ModelView;
import java.util.Map;

public interface ControllerV3 {

    ModelView process(Map<String, String> paramMap);
}
