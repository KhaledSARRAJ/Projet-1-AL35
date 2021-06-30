package fr.eql.al35.WebSocketsChat.model;



import lombok.*;


import java.io.Serializable;


@Getter @Setter @ToString
@NoArgsConstructor
public class User implements Serializable {
    private Long id;
    private String prenom;
    private String mail;

}
