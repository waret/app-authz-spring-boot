package net.waret.demo.photoz.web.rest.admin;

import net.waret.demo.photoz.domain.Album;
import net.waret.demo.photoz.repository.AlbumRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin")
public class AdminAlbumService {

    private final AlbumRepository albumRepository;

    @Autowired
    public AdminAlbumService(AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }

    @GetMapping("/album")
    public ResponseEntity<Map> findAll() {
        HashMap<String, List<Album>> albums = new HashMap<>();

        Iterable<Album> iterable = albumRepository.findAll();

        for (Album album : iterable) {
            albums.computeIfAbsent(album.getUserId(), k -> new ArrayList<>()).add(album);
        }

        return new ResponseEntity<>(albums, HttpStatus.OK);
    }
}
