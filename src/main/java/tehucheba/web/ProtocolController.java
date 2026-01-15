package tehucheba.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import tehucheba.model.Protocol;
import tehucheba.repository.ProtocolRepository;

import java.util.List;

@Controller
@RequestMapping(value = "/protocols")
public class ProtocolController {

    private final ProtocolRepository protocolRepository;

    public ProtocolController(ProtocolRepository protocolRepository) {
        this.protocolRepository = protocolRepository;
    }

    @GetMapping()
    public String protocols(Model model) {
        List<Protocol> protocols = protocolRepository.findAll();
        model.addAttribute("protocols", protocols);
        return "protocols";
    }

    @GetMapping(value = "/{id}")
    public String viewProtocol(@PathVariable int id, Model model) {
        Protocol protocol = protocolRepository.getById(id);
        String[] details = protocol.getDetails().split(";");
        model.addAttribute("details", details);
        model.addAttribute("protocol", protocol);
        return "protocol";
    }
}
