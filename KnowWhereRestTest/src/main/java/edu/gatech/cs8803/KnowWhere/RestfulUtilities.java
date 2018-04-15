package edu.gatech.cs8803.KnowWhere;


import org.apache.http.client.methods.*;
import org.apache.http.entity.StringEntity;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;





public class RestfulUtilities {


    private String baseUrl = "https://knowwhere-c8f53.firebaseio.com/";
    private CloseableHttpClient httpclient;


    public String getUser(String username) throws Exception {
        String res;
        try {
            httpclient = HttpClients.createDefault();
            HttpGet httpRequest = new HttpGet(baseUrl + "/user/" + username + "/.json");
            httpRequest.addHeader("accept", "application/json");
            CloseableHttpResponse response = httpclient.execute(httpRequest);
            int status = response.getStatusLine().getStatusCode();
            HttpEntity entity;
            if (status >= 200 && status < 300) {
                entity = response.getEntity();
            } else {
                throw new ClientProtocolException("Unexpected response status: " + status);
            }
            res = EntityUtils.toString(entity);
            EntityUtils.consume(entity);
            response.close();
        } finally {
            httpclient.close();
        }
        return res;
    }





    public String createUser(String username, String firstname, String lastname, String email, String password) throws
            Exception {
        String res;
        try {
            httpclient = HttpClients.createDefault();

            HttpPut httpRequest = new HttpPut(baseUrl + "user/" + username +".json");
            httpRequest.addHeader("accept", "application/json");
            StringEntity input = new StringEntity("{\"username\":\"" + username + "\"," +
                    "\"firstname\":\"" + firstname + "\"," +
                    "\"lastname\":\"" + lastname + "\"," +
                    "\"email\":\"" + email + "\"," + "\"privatechat\":\"\"," +
                    "\"status\":\"available\"," + "\"coordinates\":\"\"," +
                    "\"photo\":\"\"," + "\"whatsup\":\"\"," + "\"friends\":\"\"," +
                    "\"password\":\"" + password + "\"}");
            input.setContentType("application/json");
            httpRequest.setEntity(input);

            CloseableHttpResponse response = httpclient.execute(httpRequest);
            int status = response.getStatusLine().getStatusCode();
            HttpEntity entity;
            if (status >= 200 && status < 300) {
                entity = response.getEntity();
            } else {
                throw new ClientProtocolException("Unexpected response status: " + status);
            }
            res = EntityUtils.toString(entity);
            EntityUtils.consume(entity);
            response.close();
        } finally {
            httpclient.close();
        }
        return res;
    }

    public void setUserLocation(String username, String longitude, String latitude) throws Exception {
        try {
            httpclient = HttpClients.createDefault();

            HttpPut httpRequest = new HttpPut(baseUrl + "user/" + username + "/coordinates.json");

            httpRequest.addHeader("accept", "application/json");
            StringEntity input = new StringEntity("{\"longitude\":\"" + longitude + "\"," +
                    "\"latitude\":\"" + latitude + "\"}");
            input.setContentType("application/json");
            httpRequest.setEntity(input);

            CloseableHttpResponse response = httpclient.execute(httpRequest);
            int status = response.getStatusLine().getStatusCode();
            HttpEntity entity;
            if (status >= 200 && status < 300) {
                entity = response.getEntity();
            } else {
                throw new ClientProtocolException("Unexpected response status: " + status);
            }
            EntityUtils.consume(entity);
            response.close();
        } finally {
            httpclient.close();
        }
    }



    public String getUserLocation(String username) throws Exception {
        String res;
        try {
            httpclient = HttpClients.createDefault();

            HttpGet httpRequest = new HttpGet(baseUrl + "user/" + username + "/coordinates/.json");

            httpRequest.addHeader("accept", "application/json");

            CloseableHttpResponse response = httpclient.execute(httpRequest);
            int status = response.getStatusLine().getStatusCode();
            HttpEntity entity;
            if (status >= 200 && status < 300) {
                entity = response.getEntity();
            } else {
                throw new ClientProtocolException("Unexpected response status: " + status);
            }
            res = EntityUtils.toString(entity);
            EntityUtils.consume(entity);
            response.close();
        } finally {
            httpclient.close();
        }
        return res;
    }

    public void postProfileMessage(String username, String message) throws Exception {
        try {
            httpclient = HttpClients.createDefault();

            HttpPatch httpRequest = new HttpPatch(baseUrl + "user/" + username + ".json");

            httpRequest.addHeader("accept", "application/json");
            StringEntity input = new StringEntity("{\"whatsup\":\"" + message + "\"}");
            input.setContentType("application/json");
            httpRequest.setEntity(input);

            CloseableHttpResponse response = httpclient.execute(httpRequest);
            int status = response.getStatusLine().getStatusCode();
            HttpEntity entity;
            if (status >= 200 && status < 300) {
                entity = response.getEntity();
            } else {
                throw new ClientProtocolException("Unexpected response status: " + status);
            }
            EntityUtils.consume(entity);
            response.close();
        } finally {
            httpclient.close();
        }
    }

    public String getProfileMessage(String username) throws Exception {
        String res;
        try {
            httpclient = HttpClients.createDefault();

            HttpGet httpRequest = new HttpGet(baseUrl + "user/" + username + "/whatsup.json");

            httpRequest.addHeader("accept", "application/json");


            CloseableHttpResponse response = httpclient.execute(httpRequest);
            int status = response.getStatusLine().getStatusCode();
            HttpEntity entity;
            if (status >= 200 && status < 300) {
                entity = response.getEntity();
            } else {
                throw new ClientProtocolException("Unexpected response status: " + status);
            }
            res = EntityUtils.toString(entity);
            EntityUtils.consume(entity);
            response.close();
        } finally {
            httpclient.close();
        }
        return res;
    }

    public void updateStatus(String username, String userstatus) throws Exception {
        try {
            httpclient = HttpClients.createDefault();

            HttpPatch httpRequest = new HttpPatch(baseUrl + "user/" + username + ".json");

            httpRequest.addHeader("accept", "application/json");
            StringEntity input = new StringEntity("{\"status\":\"" + userstatus + "\"}");
            input.setContentType("application/json");
            httpRequest.setEntity(input);

            CloseableHttpResponse response = httpclient.execute(httpRequest);
            int status = response.getStatusLine().getStatusCode();
            HttpEntity entity;
            if (status >= 200 && status < 300) {
                entity = response.getEntity();
            } else {
                throw new ClientProtocolException("Unexpected response status: " + status);
            }
            EntityUtils.consume(entity);
            response.close();
        } finally {
            httpclient.close();
        }
    }

    public String getStatus(String username) throws Exception {
        String res;
        try {
            httpclient = HttpClients.createDefault();

            HttpGet httpRequest = new HttpGet(baseUrl + "user/" + username + "/status.json");

            httpRequest.addHeader("accept", "application/json");


            CloseableHttpResponse response = httpclient.execute(httpRequest);
            int status = response.getStatusLine().getStatusCode();
            HttpEntity entity;
            if (status >= 200 && status < 300) {
                entity = response.getEntity();
            } else {
                throw new ClientProtocolException("Unexpected response status: " + status);
            }
            res = EntityUtils.toString(entity);
            EntityUtils.consume(entity);
            response.close();
        } finally {
            httpclient.close();
        }
        return res;
    }

    public void updatePhoto(String username, String photo_url) throws Exception {
        try {
            httpclient = HttpClients.createDefault();

            HttpPatch httpRequest = new HttpPatch(baseUrl + "user/" + username + ".json");

            httpRequest.addHeader("accept", "application/json");
            StringEntity input = new StringEntity("{\"photo\":\"" + photo_url + "\"}");
            input.setContentType("application/json");
            httpRequest.setEntity(input);

            CloseableHttpResponse response = httpclient.execute(httpRequest);
            int status = response.getStatusLine().getStatusCode();
            HttpEntity entity;
            if (status >= 200 && status < 300) {
                entity = response.getEntity();
            } else {
                throw new ClientProtocolException("Unexpected response status: " + status);
            }
            EntityUtils.consume(entity);
            response.close();
        } finally {
            httpclient.close();
        }
    }

    public String getPhoto(String username) throws Exception {
        String res;
        try {
            httpclient = HttpClients.createDefault();

            HttpGet httpRequest = new HttpGet(baseUrl + "user/" + username + "/photo.json");

            httpRequest.addHeader("accept", "application/json");


            CloseableHttpResponse response = httpclient.execute(httpRequest);
            int status = response.getStatusLine().getStatusCode();
            HttpEntity entity;
            if (status >= 200 && status < 300) {
                entity = response.getEntity();
            } else {
                throw new ClientProtocolException("Unexpected response status: " + status);
            }
            res = EntityUtils.toString(entity);
            EntityUtils.consume(entity);
            response.close();
        } finally {
            httpclient.close();
        }
        return res;
    }




    public String createGroup(String groupname, String groupdecription) throws Exception {
        String res;
        try {
            httpclient = HttpClients.createDefault();
            HttpPut httpRequest = new HttpPut(baseUrl + "group/" + groupname +".json");
            httpRequest.addHeader("accept", "application/json");
            StringEntity input = new StringEntity("{\"groupname\":\"" + groupname + "\"," +
                    "\"groupdecription\":\"" + groupdecription + "\"," +
                    "\"groupchat\":\"\"," +
                    "\"members\":\"\"}");
            input.setContentType("application/json");
            httpRequest.setEntity(input);

            CloseableHttpResponse response = httpclient.execute(httpRequest);
            int status = response.getStatusLine().getStatusCode();
            HttpEntity entity;
            if (status >= 200 && status < 300) {
                entity = response.getEntity();
            } else {
                throw new ClientProtocolException("Unexpected response status: " + status);
            }
            res = EntityUtils.toString(entity);
            EntityUtils.consume(entity);
            response.close();
        } finally {
            httpclient.close();
        }
        return res;

    }



    public String getGroup(String groupname) throws Exception {
        String res;
        try {
            httpclient = HttpClients.createDefault();
            HttpGet httpRequest = new HttpGet(baseUrl + "/group/" + groupname + "/.json");
            httpRequest.addHeader("accept", "application/json");
            CloseableHttpResponse response = httpclient.execute(httpRequest);
            int status = response.getStatusLine().getStatusCode();
            HttpEntity entity;
            if (status >= 200 && status < 300) {
                entity = response.getEntity();
            } else {
                throw new ClientProtocolException("Unexpected response status: " + status);
            }
            res = EntityUtils.toString(entity);
            EntityUtils.consume(entity);
            response.close();
        } finally {
            httpclient.close();
        }
        return res;

    }




    public String getGroupMembers(String groupname) throws Exception {
        String lst, res = "";
        try {
            httpclient = HttpClients.createDefault();
            HttpGet httpRequest = new HttpGet(baseUrl + "/group/" + groupname + "/members/.json");
            httpRequest.addHeader("accept", "application/json");
            CloseableHttpResponse response = httpclient.execute(httpRequest);
            int status = response.getStatusLine().getStatusCode();
            HttpEntity entity;
            if (status >= 200 && status < 300) {
                entity = response.getEntity();
            } else {
                throw new ClientProtocolException("Unexpected response status: " + status);
            }
            lst = EntityUtils.toString(entity);
            EntityUtils.consume(entity);
            response.close();
        } finally {
            httpclient.close();
        }
        String[] tokens = lst.substring(1, lst.length() - 1).split(",");
        for (int i = 0; i < tokens.length; i += 1) {
            String username = tokens[i].substring(1, tokens[i].length() - 4);
            res += username + ",";
        }
        return res.substring(0, res.length() - 1);
    }



    public void addMember(String groupname, String username) throws Exception {
        try {
            httpclient = HttpClients.createDefault();
            HttpPatch httpRequest = new HttpPatch(baseUrl + "group/" + groupname + "/members.json");
            httpRequest.addHeader("accept", "application/json");
            StringEntity input = new StringEntity("{\"" + username + "\": \"\"}");
            input.setContentType("application/json");
            httpRequest.setEntity(input);

            CloseableHttpResponse response = httpclient.execute(httpRequest);
            int status = response.getStatusLine().getStatusCode();
            HttpEntity entity;
            if (status >= 200 && status < 300) {
                entity = response.getEntity();
            } else {
                throw new ClientProtocolException("Unexpected response status: " + status);
            }
            EntityUtils.consume(entity);
            response.close();
        } finally {
            httpclient.close();
        }

    }

    public void deleteMember(String groupname, String username) throws Exception {
        try {
            httpclient = HttpClients.createDefault();

            HttpDelete httpDelete = new HttpDelete(baseUrl + "group/" + groupname + "/members/" + username +".json");
            httpDelete.addHeader("accept", "application/json");

            CloseableHttpResponse response = httpclient.execute(httpDelete);
            int status = response.getStatusLine().getStatusCode();
            HttpEntity entity;
            if (status >= 200 && status < 300) {
                entity = response.getEntity();
            } else {
                throw new ClientProtocolException("Unexpected response status: " + status);
            }
            EntityUtils.consume(entity);
            response.close();
        } finally {
            httpclient.close();
        }
    }

    public void deleteGroup(String groupname) throws Exception {
        try {
            httpclient = HttpClients.createDefault();

            HttpDelete httpDelete = new HttpDelete(baseUrl + "group/" + groupname + ".json");
            httpDelete.addHeader("accept", "application/json");

            CloseableHttpResponse response = httpclient.execute(httpDelete);
            int status = response.getStatusLine().getStatusCode();
            HttpEntity entity;
            if (status >= 200 && status < 300) {
                entity = response.getEntity();
            } else {
                throw new ClientProtocolException("Unexpected response status: " + status);
            }
            EntityUtils.consume(entity);
            response.close();
        } finally {
            httpclient.close();
        }
    }


    public String createPrivateChat(String sender, String receiver, String timestamp, String content) throws
            Exception {
        String res;
        String mid = sender + timestamp;
        try {
            httpclient = HttpClients.createDefault();
            HttpPut httpRequest = new HttpPut(baseUrl + "privatechat/" + mid +".json");
            httpRequest.addHeader("accept", "application/json");
            StringEntity input = new StringEntity("{\"sender\":\"" + sender + "\"," +
                    "\"receiver\":\"" + receiver + "\"," +
                    "\"timestamp\":\"" + timestamp + "\"," +
                    "\"content\":\"" + content + "\"}");
            input.setContentType("application/json");
            httpRequest.setEntity(input);

            CloseableHttpResponse response = httpclient.execute(httpRequest);
            int status = response.getStatusLine().getStatusCode();
            HttpEntity entity;
            if (status >= 200 && status < 300) {
                entity = response.getEntity();
            } else {
                throw new ClientProtocolException("Unexpected response status: " + status);
            }
            res = EntityUtils.toString(entity);
            EntityUtils.consume(entity);
            response.close();
        } finally {
            httpclient.close();
        }
        addPrivateChat(sender, mid, receiver);
        addPrivateChat(receiver, mid, sender);
        return res;

    }

    private void addPrivateChat(String username, String mid, String receiver) throws Exception {

        try {
            httpclient = HttpClients.createDefault();
            HttpPatch httpRequest = new HttpPatch(baseUrl +  "user/" + username +"/privatechat/" + receiver + ".json");
            httpRequest.addHeader("accept", "application/json");
            StringEntity input = new StringEntity("{\"" + mid + "\":\"\"}");
            input.setContentType("application/json");
            httpRequest.setEntity(input);

            CloseableHttpResponse response = httpclient.execute(httpRequest);
            int status = response.getStatusLine().getStatusCode();
            HttpEntity entity;
            if (status >= 200 && status < 300) {
                entity = response.getEntity();
            } else {
                throw new ClientProtocolException("Unexpected response status: " + status);
            }

            EntityUtils.consume(entity);
            response.close();
        } finally {
            httpclient.close();
        }


    }

    public String createGroupChat(String groupname, String sender, String timestamp, String content) throws
            Exception {
        String res;
        String mid = groupname + timestamp;
        try {
            httpclient = HttpClients.createDefault();
            HttpPut httpRequest = new HttpPut(baseUrl + "groupchat/" + mid +".json");
            httpRequest.addHeader("accept", "application/json");
            StringEntity input = new StringEntity("{\"groupname\":\"" + groupname + "\"," +
                    "\"sender\":\"" + sender + "\"," +
                    "\"timestamp\":\"" + timestamp + "\"," +
                    "\"content\":\"" + content + "\"}");
            input.setContentType("application/json");
            httpRequest.setEntity(input);

            CloseableHttpResponse response = httpclient.execute(httpRequest);
            int status = response.getStatusLine().getStatusCode();
            HttpEntity entity;
            if (status >= 200 && status < 300) {
                entity = response.getEntity();
            } else {
                throw new ClientProtocolException("Unexpected response status: " + status);
            }
            res = EntityUtils.toString(entity);
            EntityUtils.consume(entity);
            response.close();
        } finally {
            httpclient.close();
        }
        addGroupChat(groupname, mid);
        return res;

    }

    private void addGroupChat(String groupname, String mid) throws Exception {

        try {
            httpclient = HttpClients.createDefault();
            HttpPatch httpRequest = new HttpPatch(baseUrl +  "group/" + groupname +"/groupchat.json");
            httpRequest.addHeader("accept", "application/json");
            StringEntity input = new StringEntity("{\"" + mid + "\":\"\"}");
            input.setContentType("application/json");
            httpRequest.setEntity(input);

            CloseableHttpResponse response = httpclient.execute(httpRequest);
            int status = response.getStatusLine().getStatusCode();
            HttpEntity entity;
            if (status >= 200 && status < 300) {
                entity = response.getEntity();
            } else {
                throw new ClientProtocolException("Unexpected response status: " + status);
            }

            EntityUtils.consume(entity);
            response.close();
        } finally {
            httpclient.close();
        }
    }

    public String getGroupChat(String groupname) throws Exception {
        String res = "", lst;
        try {
            httpclient = HttpClients.createDefault();
            HttpGet httpRequest = new HttpGet(baseUrl + "group/" + groupname + "/groupchat.json");
            httpRequest.addHeader("accept", "application/json");


            CloseableHttpResponse response = httpclient.execute(httpRequest);
            int status = response.getStatusLine().getStatusCode();
            HttpEntity entity;
            if (status >= 200 && status < 300) {
                entity = response.getEntity();
            } else {
                throw new ClientProtocolException("Unexpected response status: " + status);
            }
            lst = EntityUtils.toString(entity);
            EntityUtils.consume(entity);
            response.close();
        } finally {
            httpclient.close();
        }
        String[] tokens = lst.substring(1, lst.length() - 1).split(",");
        for (int i = 0; i < tokens.length; i += 1) {
            String mid = tokens[i].substring(1, tokens[i].length() - 4);
            res += getChatById(mid, "groupchat");
        }
        return res;
    }

    private String getChatById(String mid, String type) throws
            Exception {
        String res;
        try {
            httpclient = HttpClients.createDefault();
            HttpGet httpRequest = new HttpGet(baseUrl + type + "/" + mid + ".json");
            httpRequest.addHeader("accept", "application/json");


            CloseableHttpResponse response = httpclient.execute(httpRequest);
            int status = response.getStatusLine().getStatusCode();
            HttpEntity entity;
            if (status >= 200 && status < 300) {
                entity = response.getEntity();
            } else {
                throw new ClientProtocolException("Unexpected response status: " + status);
            }
            res = EntityUtils.toString(entity);
            EntityUtils.consume(entity);
            response.close();
        } finally {
            httpclient.close();
        }
        return res;
    }

    public String getPrivateChat(String username1, String username2) throws
            Exception {
        String res = "", lst;
        try {
            httpclient = HttpClients.createDefault();
            HttpGet httpRequest = new HttpGet(baseUrl + "user/" + username1 + "/privatechat/" + username2 +".json");
            httpRequest.addHeader("accept", "application/json");


            CloseableHttpResponse response = httpclient.execute(httpRequest);
            int status = response.getStatusLine().getStatusCode();
            HttpEntity entity;
            if (status >= 200 && status < 300) {
                entity = response.getEntity();
            } else {
                throw new ClientProtocolException("Unexpected response status: " + status);
            }
            lst = EntityUtils.toString(entity);
            EntityUtils.consume(entity);
            response.close();
        } finally {
            httpclient.close();
        }
        String[] tokens = lst.substring(1, lst.length() - 1).split(",");
        for (int i = 0; i < tokens.length; i += 1) {
            String mid = tokens[i].substring(1, tokens[i].length() - 4);
            res += getChatById(mid, "privatechat");
        }
        return res;
    }

    public void addFriend(String username1, String username2) throws Exception {
        addFriendHelper(username1, username2);
        addFriendHelper(username2, username1);
    }


    private void addFriendHelper(String username, String friend) throws Exception {
        try {
            httpclient = HttpClients.createDefault();
            HttpPatch httpRequest = new HttpPatch(baseUrl + "user/" + username + "/friends.json");
            httpRequest.addHeader("accept", "application/json");
            StringEntity input = new StringEntity("{\"" + friend + "\": \"\"}");
            input.setContentType("application/json");
            httpRequest.setEntity(input);

            CloseableHttpResponse response = httpclient.execute(httpRequest);
            int status = response.getStatusLine().getStatusCode();
            HttpEntity entity;
            if (status >= 200 && status < 300) {
                entity = response.getEntity();
            } else {
                throw new ClientProtocolException("Unexpected response status: " + status);
            }
            EntityUtils.consume(entity);
            response.close();
        } finally {
            httpclient.close();
        }

    }

    private String getFriends(String username) throws Exception {
        String res = "", lst;
        try {
            httpclient = HttpClients.createDefault();
            HttpGet httpRequest = new HttpGet(baseUrl + "user/" + username + "/friends.json");
            httpRequest.addHeader("accept", "application/json");

            CloseableHttpResponse response = httpclient.execute(httpRequest);
            int status = response.getStatusLine().getStatusCode();
            HttpEntity entity;
            if (status >= 200 && status < 300) {
                entity = response.getEntity();
            } else {
                throw new ClientProtocolException("Unexpected response status: " + status);
            }
            lst = EntityUtils.toString(entity);
            EntityUtils.consume(entity);
            response.close();
        } finally {
            httpclient.close();
        }
        String[] tokens = lst.substring(1, lst.length() - 1).split(",");
        for (int i = 0; i < tokens.length; i += 1) {
            String friendname = tokens[i].substring(1, tokens[i].length() - 4);
            res += friendname + ",";
        }
        return res.substring(0, res.length() - 1);

    }





    public static void main(String[] args) throws Exception {
        RestfulUtilities restfulUtilities = new RestfulUtilities();
        restfulUtilities.createUser("Joe", "Yi", "Zhang", "email@email.com", "123456");
        restfulUtilities.createUser("Tina", "Hong", "Xiao", "email@email.com", "123456");
        restfulUtilities.setUserLocation("Joe", "33", "87");
        restfulUtilities.setUserLocation("Joe", "34", "86");
        System.out.println("Joe's location is " + restfulUtilities.getUserLocation("Joe"));
        restfulUtilities.postProfileMessage("Joe", "Some dreams never sleep");
        System.out.println("Joe's profile message is " + restfulUtilities.getProfileMessage("Joe"));
        restfulUtilities.updateStatus("Joe", "busy");
        System.out.println("Joe's status is " + restfulUtilities.getStatus("Joe"));
        restfulUtilities.updatePhoto("Joe", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRd80OTpVAF8d7Zte7x0LsGdHHPIrzuIhPYQ89xAmccdp35ErHI4Q");
        System.out.println("Joe's photo_url is " + restfulUtilities.getPhoto("Joe"));
        restfulUtilities.addFriend("Joe", "Tina");
        System.out.println("Joe's friends are " + restfulUtilities.getFriends("Joe"));
        restfulUtilities.createGroup("Publix", "Let's go shopping");
        restfulUtilities.addMember("Publix", "Joe");
        restfulUtilities.addMember("Publix", "Tina");
        System.out.println("Publix group members are " + restfulUtilities.getGroupMembers("Publix"));
        restfulUtilities.deleteMember("Publix", "Tina");
        System.out.println("Publix group members are " + restfulUtilities.getGroupMembers("Publix"));
        restfulUtilities.addMember("Publix", "Tina");
        System.out.println("Publix group members are " + restfulUtilities.getGroupMembers("Publix"));
        restfulUtilities.createPrivateChat("Joe", "Tina", "100", "What are you doing?");
        restfulUtilities.createPrivateChat("Tina", "Joe", "101", "Fine, thank you, and you?");
        restfulUtilities.createGroupChat("Publix", "Tina", "200", "Hello!");
        System.out.println("Publix information is " + restfulUtilities.getGroup("Publix"));
        System.out.println("Joe information is " + restfulUtilities.getUser("Joe"));
        System.out.println("Joe and Tina chat history is " + restfulUtilities.getPrivateChat("Joe", "Tina"));
        restfulUtilities.createGroupChat("Publix", "Joe", "205", "Hello, Tina!");
        System.out.println("Publix group chat history is " + restfulUtilities.getGroupChat("Publix"));

    }


}
