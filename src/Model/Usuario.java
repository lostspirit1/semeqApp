/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author SpiriT
 */
public class Usuario {
    private static Usuario instance;
    private Long id;
    private String login;
    private String senha;
    private String nome;
    
 
    public Usuario(String login,String senha) {
        this.login = login;
        this.senha = senha;
    }
    
    public Usuario(Long id) {
        this.id = id;
    }

    // construtores
    public Usuario(){}
    
    
   //sets/gets 
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    } 
    
   public void setId(Long id){
        this.id=id;
    }
    public Long getId(){
        return id;
    }
    public void setLogin(String login){
        this.login=login;
    }
    public String getLogin(){
        return login;
    }
    public void setSenha(String senha){
        this.senha=senha;
    }
    public String getSenha(){
        return senha;
    }
    
    public static Usuario getInstance() {
        if (instance == null){
            instance = new Usuario();
            }
        return instance;
    }
}
