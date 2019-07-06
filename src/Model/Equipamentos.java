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
public class Equipamentos {

    private Long id_equipamentos;
    private String equipamento_nome,serial_equipamento;
    private Usuario idUsuario; 
    private Long id_equipamento_do_Usuario;
    private Long id_equipamentoUser_computador,id_equipamentoUser_monitor,id_equipamentoUser_impressora,id_equipamentoUser_modem;
    private String serial_computador,serial_monitor,serial_impressora,serial_modem;
    
    public Long getId_equipamentoUser_computador() {
        return id_equipamentoUser_computador;
    }

    public void setId_equipamentoUser_computador(Long id_equipamentoUser_computador) {
        this.id_equipamentoUser_computador = id_equipamentoUser_computador;
    }

    public Long getId_equipamentoUser_monitor() {
        return id_equipamentoUser_monitor;
    }

    public void setId_equipamentoUser_monitor(Long id_equipamentoUser_monitor) {
        this.id_equipamentoUser_monitor = id_equipamentoUser_monitor;
    }

    public Long getId_equipamentoUser_impressora() {
        return id_equipamentoUser_impressora;
    }

    public void setId_equipamentoUser_impressora(Long id_equipamentoUser_impressora) {
        this.id_equipamentoUser_impressora = id_equipamentoUser_impressora;
    }

    public Long getId_equipamentoUser_modem() {
        return id_equipamentoUser_modem;
    }

    public void setId_equipamentoUser_modem(Long id_equipamentoUser_modem) {
        this.id_equipamentoUser_modem = id_equipamentoUser_modem;
    }

    public String getSerial_computador() {
        return serial_computador;
    }

    public void setSerial_computador(String serial_computador) {
        this.serial_computador = serial_computador;
    }

    public String getSerial_monitor() {
        return serial_monitor;
    }

    public void setSerial_monitor(String serial_monitor) {
        this.serial_monitor = serial_monitor;
    }

    public String getSerial_impressora() {
        return serial_impressora;
    }

    public void setSerial_impressora(String serial_impressora) {
        this.serial_impressora = serial_impressora;
    }

    public String getSerial_modem() {
        return serial_modem;
    }

    public void setSerial_modem(String serial_modem) {
        this.serial_modem = serial_modem;
    }


    public Equipamentos(Long id_equipamentos, String equipamento_nome, String serial_equipamento, Long id_equipamento_do_Usuario) {
        this.id_equipamentos = id_equipamentos;
        this.equipamento_nome = equipamento_nome;
        this.serial_equipamento = serial_equipamento;
        this.id_equipamento_do_Usuario = id_equipamento_do_Usuario;
    }
    public Equipamentos(){}

    public Long getId_equipamentos() {
        return id_equipamentos;
    }

    public void setId_equipamentos(Long id_equipamentos) {
        this.id_equipamentos = id_equipamentos;
    }

    public String getEquipamento_nome() {
        return equipamento_nome;
    }

    public void setEquipamento_nome(String equipamento_nome) {
        this.equipamento_nome = equipamento_nome;
    }

    public String getSerial_equipamento() {
        return serial_equipamento;
    }

    public void setSerial_equipamento(String serial_equipamento) {
        this.serial_equipamento = serial_equipamento;
    }

    public Usuario getEquipamentoIDUsuario() {
        return idUsuario;
    }

    public void setEquipamentoIDUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Long getId_equipamento_do_Usuario() {
        return id_equipamento_do_Usuario;
    }

    public void setId_equipamento_do_Usuario(Long id_equipamento_do_Usuario) {
        this.id_equipamento_do_Usuario = id_equipamento_do_Usuario;
    }

    @Override
    public String toString() {
        return getEquipamento_nome(); //To change body of generated methods, choose Tools | Templates.
    }

    
    
}
