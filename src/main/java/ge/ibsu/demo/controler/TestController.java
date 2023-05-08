package ge.ibsu.demo.controler;

import ge.ibsu.demo.dto.Test;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @RequestMapping(value = "/call", method = RequestMethod.GET, produces = {"application/json"})
    public Test test() {
        return new Test("irakli", "philipia");
    }

    @RequestMapping(value = "/post", method = RequestMethod.POST, produces = {"application/json"})
    public Test testPost(@RequestBody Test test) {
        System.out.println(test.getFirstName() + "" + test.getLastName());
        return new Test(test.getFirstName() + "from server", test.getLastName());
    }
}
