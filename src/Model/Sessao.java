package Model;

import DAO.PecasDAO;
import java.util.List;

public class Sessao {

    private static Sessao instance = null;
    private Usuario usuario;
    private Matricula matricula;
    private Boletim boletim;
    private Status status;
    private List<Requisicao> requisicaolist;
    private static List<Pecas> pecasList;
    private Departamento departamento;
    private Equipamentos equipamentos;
    private StatusDetalhes statusDetalhes;
    private Permissao permissao;
    private ChefesDepartamentos chefes;

    private Sessao() {
    }
    public static Sessao getInstancia() {
        if (instance == null) {
            instance = new Sessao();
        }
        return instance;
    }
    public void setPecas(List<Pecas> pecasList) {
        this.pecasList = pecasList;
    }   
    public List<Pecas> getPecasList() {
    if (pecasList == null) {
        PecasDAO dao = new PecasDAO();
        pecasList = dao.pegarPecasById();
    }
    return pecasList;
    }
    
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    public Usuario getUsuario() {
        return usuario;
    }   
    public StatusDetalhes getStatusDetalhes() {
        return statusDetalhes;
    }

    public void setStatusDetalhes(StatusDetalhes statusDetalhes) {
        this.statusDetalhes = statusDetalhes;
    }

    public Permissao getPermissao() {
        return permissao;
    }

    public void setPermissao(Permissao permissao) {
        this.permissao = permissao;
    }

    public ChefesDepartamentos getChefes() {
        return chefes;
    }

    public void setChefes(ChefesDepartamentos chefes) {
        this.chefes = chefes;
    }

    public Boletim getBoletim() {
        return boletim;
    }

    public void setBoletim(Boletim boletim) {
        this.boletim = boletim;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public Equipamentos getEquipamentos() {
        return equipamentos;
    }

    public void setEquipamentos(Equipamentos equipamentos) {
        this.equipamentos = equipamentos;
    }

    public void setMatricula(Matricula matricula) {
        this.matricula = matricula;
    }

    public Matricula getMatricula() {
        return matricula;
    }

    public List<Requisicao> getRequisicaolist() {
        return requisicaolist;
    }

    public void setRequisicaolist(List<Requisicao> requisicaolist) {
        this.requisicaolist = requisicaolist;
    }

}
