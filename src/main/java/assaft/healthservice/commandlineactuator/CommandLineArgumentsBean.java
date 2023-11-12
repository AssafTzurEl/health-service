package assaft.healthservice.commandlineactuator;

import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Component;

@Component
public class CommandLineArgumentsBean {

    private final String[] sourceArgs;

    public CommandLineArgumentsBean(ApplicationArguments args) {
        this.sourceArgs = args.getSourceArgs();
    }

    public String[] getSourceArgs() {
        return sourceArgs;
    }
}
