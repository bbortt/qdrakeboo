package io.github.bbortt.qdrakeboo.upstreamserver.web;

import io.github.bbortt.qdrakeboo.upstreamserver.domain.Movie;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class UploadController {

  private static final Logger LOGGER = LoggerFactory.getLogger(UploadController.class);

  @PostMapping(path = "/", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public String receiveFile(@RequestParam("file") MultipartFile multipartFile,
      @RequestParam("metadata") Movie movie) {
    LOGGER.info("Received file: {}", multipartFile.getOriginalFilename());
    LOGGER.info("Metadata is: {}", movie);

    return UUID.randomUUID().toString();
  }
}
