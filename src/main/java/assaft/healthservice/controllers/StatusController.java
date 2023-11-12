package assaft.healthservice.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.boot.availability.ApplicationAvailability;
import org.springframework.boot.availability.AvailabilityChangeEvent;
import org.springframework.boot.availability.LivenessState;
import org.springframework.boot.availability.ReadinessState;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/status")
public class StatusController {

    public static final String HEALTHY = "/healthy";
    public static final String READY = "/ready";
    private final ApplicationAvailability applicationAvailability;
    private final ApplicationEventPublisher applicationEventPublisher;

    public StatusController(ApplicationAvailability applicationAvailability,
                            ApplicationEventPublisher applicationEventPublisher) {
        this.applicationAvailability = applicationAvailability;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @GetMapping(HEALTHY)
    @Operation(summary = "Get liveness", description = "Check liveness status")
    @ApiResponse(responseCode = "200", description = "Liveness status of the application")
    public boolean getLiveness() {
        return applicationAvailability.getLivenessState() == LivenessState.CORRECT;
    }

    @PutMapping(HEALTHY)
    @Operation(summary = "Set liveness status", description = "Updates the liveness status of the application")
    @ApiResponse(responseCode = "200", description = "Liveness status updated")
    public void setLiveness(@Parameter(description = "New status to set") @RequestParam boolean status) {

        var currentLivenessState = applicationAvailability.getLivenessState();
        var newLivenessState = status ? LivenessState.CORRECT : LivenessState.BROKEN;

        if (currentLivenessState != newLivenessState) {
            AvailabilityChangeEvent.publish(applicationEventPublisher, this, newLivenessState);
        }
    }

    @GetMapping(READY)
    @Operation(summary = "Get readiness", description = "Check readiness status")
    @ApiResponse(responseCode = "200", description = "Readiness status of the application")
    public boolean isReadiness() {
        return applicationAvailability.getReadinessState() == ReadinessState.ACCEPTING_TRAFFIC;
    }

    @PutMapping(READY)
    @Operation(summary = "Set readiness status", description = "Updates the readiness status of the application")
    @ApiResponse(responseCode = "200", description = "Readiness status updated")
    public void setReadiness(@Parameter(description = "New status to set") @RequestParam boolean status) {

        var currentReadinessState = applicationAvailability.getReadinessState();
        var newReadinessState =
                status ? ReadinessState.ACCEPTING_TRAFFIC : ReadinessState.REFUSING_TRAFFIC;

        if (currentReadinessState != newReadinessState) {
            AvailabilityChangeEvent.publish(applicationEventPublisher, this, newReadinessState);
        }
    }
}
