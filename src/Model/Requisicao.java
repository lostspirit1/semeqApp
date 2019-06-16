
package Model;

/**
 *
 * @author SpiriT
 */
public class Requisicao extends Usuario {
    private Long id;
    private String nome;

    public Requisicao(String nome) {
        this.nome = nome;
    }
    public Requisicao(){}
    public Requisicao(Long id){
    this.id=id;
    }
    public Requisicao(Long id, String nome){
    this.nome=nome;
    }
    public void setId(Long id){
    this.id=id;
    }
    public Long getId(){
    return id;
    }
    public void setNome(String nome){
    this.nome=nome;
    }
    public String getNome(){
    return nome;
    }
}
