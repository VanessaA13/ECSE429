using System.Text;
using Newtonsoft.Json.Linq;

namespace Projects
{
    public class Tests
    {
        readonly string url = "http://localhost:4567/projects";
        readonly string projectsPrefix="{\"projects\":[";
        readonly string projectsPostfix="]}";
        readonly string project1_order1="{\"id\":\"1\",\"title\":\"Office Work\",\"completed\":\"false\",\"active\":\"false\",\"description\":\"\",\"tasks\":[{\"id\":\"1\"},{\"id\":\"2\"}]}";
        readonly string project1_order2="{\"id\":\"1\",\"title\":\"Office Work\",\"completed\":\"false\",\"active\":\"false\",\"description\":\"\",\"tasks\":[{\"id\":\"2\"},{\"id\":\"1\"}]}";
        readonly string project2="{\"id\":\"2\",\"title\":\"title2\",\"completed\":\"false\",\"active\":\"false\",\"description\":\"description2\",\"tasks\":[{\"id\":\"2\"}]}";
        readonly string project3="{\"id\":\"3\",\"title\":\"title3\",\"completed\":\"true\",\"active\":\"true\",\"description\":\"description3\",\"tasks\":[{\"id\":\"1\"}]}";
        readonly string project3_new1="{\"id\":\"3\",\"title\":\"title3-new1\",\"completed\":\"true\",\"active\":\"true\",\"description\":\"description3-new1\",\"tasks\":[{\"id\":\"1\"}]}";
        readonly string project3_new2="{\"id\":\"3\",\"title\":\"title3-new2\",\"completed\":\"false\",\"active\":\"false\",\"description\":\"description3-new2\",\"tasks\":[{\"id\":\"1\"},{\"id\":\"2\"}]}";
        readonly string task2_order1="{\"todos\":[{\"id\":\"2\",\"title\":\"file paperwork\",\"doneStatus\":\"false\",\"description\":\"\",\"tasksof\":[{\"id\":\"1\"},{\"id\":\"2\"}]}]}";
        readonly string task2_order2="{\"todos\":[{\"id\":\"2\",\"title\":\"file paperwork\",\"doneStatus\":\"false\",\"description\":\"\",\"tasksof\":[{\"id\":\"2\"},{\"id\":\"1\"}]}]}";
        readonly string task2_new="{\"projects\":[{\"id\":\"2\",\"title\":\"title2\",\"completed\":\"false\",\"active\":\"false\",\"description\":\"description2\",\"tasks\":[{\"id\":\"2\"},{\"id\":\"1\"}]}]}";

        [SetUp]
        public void Setup()
        {   
        }


        [Test]
        public void GET_Projects_1()
        {
            try{
                string response = SendGetRequestAsync(url).Result;
                string responseJSON = JObject.Parse(response).ToString();
                string expected1=projectsPrefix+project1_order1+projectsPostfix;
                string expected2=projectsPrefix+project1_order2+projectsPostfix;
                string expected1JSON = JObject.Parse(expected1).ToString();
                string expected2JSON = JObject.Parse(expected2).ToString();
                Boolean equals = responseJSON.Equals(expected1JSON) || responseJSON.Equals(expected2JSON);
                Assert.That(equals, Is.True);
            }catch {
                Assert.Fail();
            }
        }


        [Test]
        public void HEAD_Projects_1()
        {   
            try{
                string response = SendHeadRequestAsync(url).Result;

                Assert.That(response, Is.Not.EqualTo(""));
            }catch {
                Assert.Fail();
            }
        }


        [Test]
        public void POST_Projects_1()
        {
            try{
                string project2_post="{\"title\":\"title2\",\"completed\":false,\"active\":false,\"description\":\"description2\",\"tasks\":[{\"id\":\"2\"}]}";
                string response = SendPostRequestAsync(url, project2_post).Result;
                string actual = SendGetRequestAsync(url+"/2").Result;
                JObject actualJSON = JObject.Parse(actual);
                JObject expectedJSON = JObject.Parse(projectsPrefix+project2+projectsPostfix);
                Assert.That(actualJSON.ToString(), Is.EqualTo(expectedJSON.ToString()));
            }catch {
                Assert.Fail();
            }
        }

        [Test]
        public void POST_Projects_2()
        {
            try{
                string project3_post="{\"title\":\"title3\",\"completed\":true,\"active\":true,\"description\":\"description3\",\"tasks\":[{\"id\":\"1\"}]}";
                string response = SendPostRequestAsync(url, project3_post).Result;
                string actual = SendGetRequestAsync(url+"/3").Result;
                JObject actualJSON = JObject.Parse(actual);
                JObject expectedJSON = JObject.Parse(projectsPrefix+project3+projectsPostfix);
                Assert.That(actualJSON.ToString(), Is.EqualTo(expectedJSON.ToString()));
            }catch {
                Assert.Fail();
            }
        }

        [Test]
        public void GET_Projects_2()
        {
            try{
                string response = SendGetRequestAsync(url).Result;
                string responseJSON = JObject.Parse(response).ToString();

                string expected1=projectsPrefix+project3+","+project2+","+project1_order1+projectsPostfix;
                string expected2=projectsPrefix+project3+","+project2+","+project1_order2+projectsPostfix;
                string expected3=projectsPrefix+project2+","+project3+","+project1_order1+projectsPostfix;
                string expected4=projectsPrefix+project2+","+project3+","+project1_order2+projectsPostfix;
                string expected5=projectsPrefix+project2+","+project1_order1+","+project3+projectsPostfix;
                string expected6=projectsPrefix+project2+","+project1_order2+","+project3+projectsPostfix;
                string expected7=projectsPrefix+project3+","+project1_order1+","+project2+projectsPostfix;
                string expected8=projectsPrefix+project3+","+project1_order2+","+project2+projectsPostfix;
                string expected9=projectsPrefix+project1_order1+","+project2+","+project3+projectsPostfix;
                string expected10=projectsPrefix+project1_order2+","+project2+","+project3+projectsPostfix;
                string expected11=projectsPrefix+project1_order1+","+project3+","+project2+projectsPostfix;
                string expected12=projectsPrefix+project1_order2+","+project3+","+project2+projectsPostfix;
                string expected1JSON = JObject.Parse(expected1).ToString();
                string expected2JSON = JObject.Parse(expected2).ToString();
                string expected3JSON = JObject.Parse(expected3).ToString();
                string expected4JSON = JObject.Parse(expected4).ToString();
                string expected5JSON = JObject.Parse(expected5).ToString();
                string expected6JSON = JObject.Parse(expected6).ToString();
                string expected7JSON = JObject.Parse(expected7).ToString();
                string expected8JSON = JObject.Parse(expected8).ToString();
                string expected9JSON = JObject.Parse(expected9).ToString();
                string expected10JSON = JObject.Parse(expected10).ToString();
                string expected11JSON = JObject.Parse(expected11).ToString();
                string expected12JSON = JObject.Parse(expected12).ToString();
                Boolean equals1 = responseJSON.Equals(expected1JSON) || responseJSON.Equals(expected2JSON) || responseJSON.Equals(expected3JSON) || responseJSON.Equals(expected4JSON);
                Boolean equals2 = responseJSON.Equals(expected5JSON) || responseJSON.Equals(expected6JSON) || responseJSON.Equals(expected7JSON) || responseJSON.Equals(expected8JSON);
                Boolean equals3 = responseJSON.Equals(expected9JSON) || responseJSON.Equals(expected10JSON) || responseJSON.Equals(expected11JSON) || responseJSON.Equals(expected12JSON);
                Boolean equals=equals1 || equals2 || equals3;

                Assert.That(equals, Is.True);
            }catch {
                Assert.Fail();
            }
        }


        [Test]
        public void GET_Projects_Id_1()
        {
            try{
                string response = SendGetRequestAsync(url+"/1").Result;
                string responseJSON = JObject.Parse(response).ToString();
                string expected1=projectsPrefix+project1_order1+projectsPostfix;
                string expected2=projectsPrefix+project1_order2+projectsPostfix;
                string expected1JSON = JObject.Parse(expected1).ToString();
                string expected2JSON = JObject.Parse(expected2).ToString();
                Boolean equals = responseJSON.Equals(expected1JSON) || responseJSON.Equals(expected2JSON);
                Assert.That(equals, Is.True);
            }catch {
                Assert.Fail();
            }
        }

        [Test]
        public void HEAD_Projects_Id_1()
        {
            try{
                string response = SendHeadRequestAsync(url+"/1").Result;
                //Console.WriteLine("Head: " + response);
                Assert.That(response, Is.Not.EqualTo(""));
            }catch{
                Assert.Fail();
            }
        }


        [Test]
        public void POST_Projects_Id_1()
        {
            try{
                string project3_post="{\"title\":\"title3-new1\",\"completed\":true,\"active\":true,\"description\":\"description3-new1\",\"tasks\":[{\"id\":\"1\"}]}";
                string response = SendPostRequestAsync(url+"/3", project3_post).Result;
                string actual = SendGetRequestAsync(url+"/3").Result;
                JObject actualJSON = JObject.Parse(actual);
                JObject expectedJSON = JObject.Parse(projectsPrefix+project3_new1+projectsPostfix);

                Console.WriteLine(actualJSON.ToString());
                Console.WriteLine(expectedJSON.ToString());
                Assert.That(actualJSON.ToString(), Is.EqualTo(expectedJSON.ToString()));
            }catch{
                Assert.Fail();
            }
        }


        [Test]
        public void PUT_Projects_Id_1()
        {
            try{
                string project3_put="{\"title\":\"title3-new2\",\"completed\":false,\"active\":false,\"description\":\"description3-new2\",\"tasks\":[{\"id\":\"1\"},{\"id\":\"2\"}]}";
                string response = SendPutRequestAsync(url+"/3", project3_put).Result;
                //Console.WriteLine("AddTodo Test - Response: " + response);
                string actual = SendGetRequestAsync(url+"/3").Result;
                JObject actualJSON = JObject.Parse(actual);
                JObject expectedJSON = JObject.Parse(projectsPrefix+project3_new2+projectsPostfix);
            }catch {
                Assert.Fail();
            }
        }

        [Test]
        public void DELETE_Projects_Id_1()
        {
            try{
                string delete_response = SendDeleteRequestAsync(url+"/3").Result;
                if(!string.IsNullOrEmpty(delete_response)){
                    Assert.Fail();
                }
                string get_response = SendGetRequestAsync(url+"/3").Result;
                JObject get_response_JSON = JObject.Parse(get_response);
                JObject expectedJSON = JObject.Parse("{\"errorMessages\":[\"Could not find an instance with projects/3\"]}");
                Assert.That(get_response_JSON.ToString(), Is.EqualTo(expectedJSON.ToString()));
            }catch {
                Assert.Fail();
            }
        }


        [Test]
        public void GET_Projects_Id_Tasks_1()
        {
            try{
                string response = SendGetRequestAsync(url+"/2/tasks").Result;
                string responseJSON = JObject.Parse(response).ToString();
                string expected1JSON = JObject.Parse(task2_order1).ToString();
                string expected2JSON = JObject.Parse(task2_order2).ToString();
                Boolean equals = responseJSON.Equals(expected1JSON) || responseJSON.Equals(expected2JSON);
                Assert.That(equals, Is.True);
            }catch {
                Assert.Fail();
            }
        }


        [Test]
        public void HEAD_Projects_Id_Tasks_1()
        {   
            try{
                string response = SendHeadRequestAsync(url).Result;
                Assert.That(response, Is.Not.EqualTo(""));
            }catch {
                Assert.Fail();
            }
        }

        
        [Test]
        public void POST_Projects_Id_Tasks_1()
        {   
            try{
                string post_task2 = "{\"tasks\":[{\"id\":\"1\"},{\"id\":\"2\"}]}";
                string response = SendPostRequestAsync(url+"/2", post_task2).Result;
                string actual = SendGetRequestAsync(url+"/2").Result;
                JObject actualJSON = JObject.Parse(actual);
                JObject expectedJSON = JObject.Parse(task2_new);
                Assert.That(actualJSON.ToString(), Is.EqualTo(expectedJSON.ToString()));
            }catch {
                Assert.Fail();
            }
        }

        [Test]
        public void DELETE_Projects_Id_Tasks__Id_1()
        {   
            try{
                string response = SendDeleteRequestAsync(url+"/2/tasks/1").Result;
                string actual = SendGetRequestAsync(url+"/2/tasks").Result;
                string actualJSON = JObject.Parse(actual).ToString();
                string expected1JSON = JObject.Parse(task2_order1).ToString();
                string expected2JSON = JObject.Parse(task2_order2).ToString();
                Boolean equals = actualJSON.Equals(expected1JSON) || actualJSON.Equals(expected2JSON);
                Assert.That(equals, Is.True);
            }catch {
                Assert.Fail();
            }
        }


        [Test]
        public void DELETE_Projects_Id_2()
        {
            try{
                string delete_response = SendDeleteRequestAsync(url+"/2").Result;
                if(!string.IsNullOrEmpty(delete_response)){
                    Assert.Fail();
                }
                string get_response = SendGetRequestAsync(url+"/2").Result;
                JObject get_response_JSON = JObject.Parse(get_response);
                JObject expectedJSON = JObject.Parse("{\"errorMessages\":[\"Could not find an instance with projects/2\"]}");
                Assert.That(get_response_JSON.ToString(), Is.EqualTo(expectedJSON.ToString()));
            }catch {
                Assert.Fail();
            }
        }


        private static async Task<string> SendPostRequestAsync(string url, string json)
        {
            using var client = new HttpClient();
            var content = new StringContent(json, Encoding.UTF8, "application/json");
            var response = await client.PostAsync(url, content);
            return await response.Content.ReadAsStringAsync();
        }

        private static async Task<string> SendPutRequestAsync(string url, string json)
        {
            using var client = new HttpClient();
            var content = new StringContent(json, Encoding.UTF8, "application/json");
            var response = await client.PutAsync(url, content);
            return await response.Content.ReadAsStringAsync();
        }

        private static  async Task<string> SendGetRequestAsync(string url)
        {
            using var client = new HttpClient();
            var response = await client.GetAsync(url);
            return await response.Content.ReadAsStringAsync();
        }

        private static  async Task<string> SendDeleteRequestAsync(string url)
        {
            using var client = new HttpClient();
            var response = await client.DeleteAsync(url);
            return await response.Content.ReadAsStringAsync();
        }

        private static  async Task<string> SendHeadRequestAsync(string url)
        {
            using (var client = new HttpClient())
            {
                var request = new HttpRequestMessage(HttpMethod.Head, url);
                var response = await client.SendAsync(request);

                if (response.IsSuccessStatusCode){
                    return response.ToString();
                }else{
                    return "";
                }
            }
        }

    }
}