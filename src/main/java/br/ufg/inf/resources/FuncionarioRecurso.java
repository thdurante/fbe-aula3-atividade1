package br.ufg.inf.resources;

import br.ufg.inf.dao.FuncionarioDAO;
import br.ufg.inf.dto.FuncionarioDTO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.ws.rs.*;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import java.io.IOException;
import java.util.ArrayList;

@Path("funcionarios")
public class FuncionarioRecurso implements ContainerResponseFilter {

    // GET http://localhost:8080/fbe-aula3-atividade1-1.0-SNAPSHOT/resources/funcionarios
    // GET http://localhost:8080/fbe-aula3-atividade1-1.0-SNAPSHOT/resources/funcionarios?cpf=123.456.789-10
    @GET
    @Produces("application/json; charset=utf-8")
    public String getFuncionarios(@QueryParam("cpf") String cpf) {
        String resultJSON = null;
        Gson gson = new Gson();
        ArrayList<FuncionarioDTO> funcionariosLista = null;
        FuncionarioDTO funcionario = null;

        if (cpf == null || cpf.equals("")) {
             funcionariosLista = new FuncionarioDAO().getFuncionarios();
            if(funcionariosLista == null)
                return "{ \"erro\": { \"mensagem\": \"Não foi possível recuperar a lista de funcionários da base de dados!\" }}";
        } else {
            funcionario = new FuncionarioDAO().getFuncionarioPorCpf(cpf);
            if(funcionario == null)
                return "{ \"erro\": { \"mensagem\": \"Não foi possível recuperar o funcionário com o CPF informado!\" }}";
        }

        try {
            resultJSON = (cpf == null || cpf.equals("")) ?
                    gson.toJson(funcionariosLista) :
                    gson.toJson(funcionario);
        } catch(Exception e){
            e.printStackTrace();
        }

        return resultJSON;
    }

    // GET http://localhost:8080/fbe-aula3-atividade1-1.0-SNAPSHOT/resources/funcionarios/1
    @Path("{id}")
    @GET
    @Produces("application/json; charset=utf-8")
    public String getFuncionario(@PathParam("id") String id) {
        FuncionarioDTO funcionario = null;
        String funcionarioJSON = null;
        Gson gson = new GsonBuilder().setDateFormat("dd/MM/yyyy").create();

        try {
            funcionario = new FuncionarioDAO().getFuncionario(Integer.parseInt(id));
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return "{ \"erro\": { \"mensagem\": \"Não foi possível recuperar o funcionário com o ID informado!\" }}";
        }

        if(funcionario == null)
            return "{ \"erro\": { \"mensagem\": \"Não foi possível recuperar o funcionário com o ID informado!\" }}";

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
        Gson gson = new GsonBuilder().setDateFormat("dd/MM/yyyy").create();

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
        Gson gson = new GsonBuilder().setDateFormat("dd/MM/yyyy").create();

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

    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {
        responseContext.getHeaders().add("Access-Control-Allow-Origin", "*");
        responseContext.getHeaders().add("Access-Control-Allow-Credentials", "true");
        responseContext.getHeaders().add("Access-Control-Allow-Headers", "origin, content-type, accept, authorization");
        responseContext.getHeaders().add("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD");
    }
}
