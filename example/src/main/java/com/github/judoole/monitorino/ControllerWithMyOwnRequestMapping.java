package com.github.judoole.monitorino;

import com.github.judoole.monitorino.web.MonitorinoController;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/smoketest")
public class ControllerWithMyOwnRequestMapping extends MonitorinoController {
}
