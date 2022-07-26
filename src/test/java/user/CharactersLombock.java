package user;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;

@Data
@Builder
public class CharactersLombock {

    private String name;
    private String alias;
    private String team;
    private boolean active;

}
