package assaft.healthservice.commandlineactuator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Endpoint(id = "commandline-args")
public class CommandLineArgsEndpoint {

    private final CommandLineArgumentsBean commandLineArgumentsBean;

    @Autowired
    public CommandLineArgsEndpoint(CommandLineArgumentsBean commandLineArgumentsBean) {
        this.commandLineArgumentsBean = commandLineArgumentsBean;
    }

    @ReadOperation
    public String[] commandLineArgs() {
        return commandLineArgumentsBean.getSourceArgs();
    }
}
