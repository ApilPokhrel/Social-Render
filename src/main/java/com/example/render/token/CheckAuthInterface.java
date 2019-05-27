package com.example.render.token;

import org.springframework.stereotype.Service;

import javax.servlet.ServletException;
import javax.xml.ws.ServiceMode;
import java.io.IOException;


@Service
public interface CheckAuthInterface {

    void Authentication() throws ServletException, IOException;
}
