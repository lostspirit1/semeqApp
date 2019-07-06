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
    private String novaSenha;
    private String nome;
    private String matricula;
    private Departamento dUsuario;
    private Permissao pUsuario;

    private boolean status;

    public Usuario(String login, String senha, String nome, String matricula) {
        this.login = login;
        this.senha = senha;
        this.nome = nome;
        this.matricula = matricula;
    }

    public Usuario(boolean status) {
        this.status = status;
    }

    //public Usuario(Long id, String login, String senha, String novaSenha, String nome, int adm, boolean status) {}
    public Usuario(Long id, String login, String senha, String novaSenha, String nome, int adm, boolean status) {
        this.id = id;
        this.login = login;
        this.senha = senha;
        this.novaSenha = novaSenha;
        this.nome = nome;
        this.status = status;
        this.matricula = matricula;
    }

    public Usuario(Long id, String login, String senha) {
        this.id = id;
        this.login = login;
        this.senha = senha;
    }

    public Usuario(String login, String senha) {
        this.login = login;
        this.senha = senha;
    }

    public Usuario(String login, String senha, int adm) {
        this.login = login;
        this.senha = senha;
    }

    public Usuario(String login, String senha, String novaSenha) {
        this.login = login;
        this.senha = senha;
        this.novaSenha = novaSenha;
    }

    public Usuario(Long id, String login, String senha, String nome) {
        this.id = id;
        this.login = login;
        this.senha = senha;
        this.nome = nome;
    }

    public Usuario(Long id, int adm, String login, String senha, String nome, boolean status,String matricula) {
        this.id = id;
        this.login = login;
        this.senha = senha;
        this.nome = nome;
        this.status = status;
        this.matricula=matricula;
    }

    public Usuario(Long id) {
        this.id = id;
    }

    // construtores
    public Usuario() {
    }

    //sets/gets 
    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getNome() {
        return nome;
    }

    public String getNovaSenha() {
        return novaSenha;
    }

    public void setNovaSenha(String novaSenha) {
        this.novaSenha = novaSenha;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getLogin() {
        return login;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getSenha() {
        return senha;
    }
    public Departamento getdUsuario() {
        return dUsuario;
    }

    public void setdUsuario(Departamento dUsuario) {
        this.dUsuario = dUsuario;
    }

    public Permissao getpUsuario() {
        return pUsuario;
    }

    public void setpUsuario(Permissao pUsuario) {
        this.pUsuario = pUsuario;
    }
    public static Usuario getInstance() {
        if (instance == null) {
            instance = new Usuario();
        }
        return instance;
    }
}
