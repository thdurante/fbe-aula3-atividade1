package br.ufg.inf.resources;

import br.ufg.inf.dao.FuncionarioDAO;
import br.ufg.inf.dto.FuncionarioDTO;
import com.google.gson.Gson;

import javax.ws.rs.*;
import java.util.ArrayList;

@Path("funcionarios")
public class FuncionarioRecurso {

    // GET http://localhost:8080/fbe-aula3-atividade1-1.0-SNAPSHOT/resources/funcionarios
    @GET
    @Produces("application/json; charset=utf-8")
    public String getFuncionarios() {
        String funcionariosJSON = null;
        ArrayList<FuncionarioDTO> funcionariosLista = new FuncionarioDAO().getFuncionarios();
        Gson gson = new Gson();

        if(funcionariosLista == null)
            return "{ \"erro\": { \"mensagem\": \"Não foi possível recuperar a lista de funcionários da base de dados!\" }}";

        try {
            funcionariosJSON = gson.toJson(funcionariosLista);
        } catch(Exception e){
            e.printStackTrace();
        }
        return funcionariosJSON;
    }

    // GET http://localhost:8080/fbe-aula3-atividade1-1.0-SNAPSHOT/resources/funcionarios/1
    @Path("{id}")
    @GET
    @Produces("application/json; charset=utf-8")
    public String getFuncionario(@PathParam("id") String id) {
        FuncionarioDTO funcionario = new FuncionarioDAO().getFuncionario(Integer.parseInt(id));
        String funcionarioJSON = null;
        Gson gson = new Gson();

        try {
            funcionarioJSON = gson.toJson(funcionario);
        } catch(Exception e) {
            e.printStackTrace();
        }
        return funcionarioJSON;
    }

    // POST http://localhost:8080/fbe-aula3-atividade1-1.0-SNAPSHOT/resources/funcionarios
    @POST
    @Consumes("application/json")
    @Produces("application/json; charset=utf-8")
    public String addFuncionario(String funcionarioJSON) {
        boolean result = false;
        FuncionarioDTO funcionario;
        Gson gson = new Gson();

        try {
            funcionario = gson.fromJson(funcionarioJSON, FuncionarioDTO.class);
            result = new FuncionarioDAO().addFuncionario(funcionario);
        } catch(Exception e){
            e.printStackTrace();
        }

        if (result)
            return funcionarioJSON;
        else
            return "{ \"erro\": { \"mensagem\": \"Não foi possível inserir o funcionário na base de dados!\" }}";
    }

    // PUT http://localhost:8080/fbe-aula3-atividade1-1.0-SNAPSHOT/resources/funcionarios/1
    @Path("{id}")
    @PUT
    @Consumes("application/json")
    @Produces("application/json; charset=utf-8")
    public String updateFuncionario(@PathParam("id") String id, String funcionarioJSON) {
        boolean result = false;
        FuncionarioDTO funcionario;
        Gson gson = new Gson();

        if(id == null || id.equals(""))
            return "{ \"erro\": { \"mensagem\": \"Não existe um funcionário com o id especificado!\" }}";

        if(funcionarioJSON == null || funcionarioJSON.equals(""))
            return "{ \"erro\": { \"mensagem\": \"Por favor, informe o objeto para update!\" }}";

        try {
            funcionario = gson.fromJson(funcionarioJSON, FuncionarioDTO.class);
            result = new FuncionarioDAO().updateFuncionario(Integer.parseInt(id), funcionario);
        } catch(Exception e){
            e.printStackTrace();
        }

        if (result)
            return funcionarioJSON;
        else
            return "{ \"erro\": { \"mensagem\": \"Não foi possível atualizar o funcionário na base de dados!\" }}";
    }

    // DELETE http://localhost:8080/fbe-aula3-atividade1-1.0-SNAPSHOT/resources/funcionarios/1
    @Path("{id}")
    @DELETE
    @Produces("application/json; charset=utf-8")
    public String deleteFuncionario(@PathParam("id") String id) {
        boolean result = false;

        try {
            result = new FuncionarioDAO().deleteFuncionario(Integer.parseInt(id));
        } catch(Exception e){
            e.printStackTrace();
        }

        if (result)
            return "{ \"sucesso\": { \"mensagem\": \"Funcionário excluido com sucesso!\" }}";
        else
            return "{ \"erro\": { \"mensagem\": \"Não foi possível excluir o funcionário da base de dados!\" }}";
    }
}
