
package clothrental.external;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name="order", url="${api.order.url}")
public interface ReviewedService {

    @RequestMapping(method= RequestMethod.POST, path="/orders")
    public void reviewship(@RequestBody Reviewed reviewed);

}