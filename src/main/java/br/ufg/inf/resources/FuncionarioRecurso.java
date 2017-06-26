package br.ufg.inf.resources;

import br.ufg.inf.dao.FuncionarioDAO;
import br.ufg.inf.dto.FuncionarioDTO;
import com.google.gson.Gson;

import javax.ws.rs.*;
import java.util.ArrayList;

@Path("funcionarios")
public class FuncionarioRecurso {

    @GET
    @Produces("application/json; charset=utf-8")
    public String getFuncionarios() {
        String funcionarios = null;
        ArrayList<FuncionarioDTO> funcionariosLista = new FuncionarioDAO().getFuncionarios();
        Gson gson = new Gson();

        try {
            funcionarios = gson.toJson(funcionariosLista);
        } catch(Exception e){
            e.printStackTrace();
        }
        return funcionarios;
    }

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
}
