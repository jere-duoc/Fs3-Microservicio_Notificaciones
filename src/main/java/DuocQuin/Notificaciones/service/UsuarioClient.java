package DuocQuin.Notificaciones.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import DuocQuin.Notificaciones.dto.UsuarioDTO;

@Service
public class UsuarioClient {
    @Autowired
    private RestTemplate restTemplate;

    public UsuarioDTO obtenerUsuario(Long idUsuario){
        //uso dto usuario para obtener los datos

        String url = "http://localhost:8081/api/usuarios/" + idUsuario;

        return restTemplate.getForObject(url, UsuarioDTO.class);
    }

}
