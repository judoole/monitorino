package com.github.judoole.monitorino;

import com.github.judoole.monitorino.web.HealthcheckController;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/smoketest")
public class ControllerWithMyOwnRequestMapping extends HealthcheckController {
}
