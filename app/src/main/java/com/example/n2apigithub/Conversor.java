package com.example.n2apigithub;

import org.json.JSONException;
import org.json.JSONObject;

public class Conversor {

    public UserGit getInformacao(String end){
        String json = ConexaoApi.getJsonFromApi(end);
        UserGit retorno = parseJson(json);
        return retorno;
    }

    private UserGit parseJson(String json){
        try {
            UserGit userGit = new UserGit();

            JSONObject jsonObj = new JSONObject(json);
            userGit.setNome(jsonObj.getString("name"));
            userGit.setId(jsonObj.getString("id"));
            userGit.setBio(jsonObj.getString("bio"));
            userGit.setEndereco(jsonObj.getString("location"));

            return userGit;
        }catch (JSONException e){
            e.printStackTrace();
        }
        return null;
    }

}
