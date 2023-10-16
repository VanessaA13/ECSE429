using System;
using System.Net.Http;
using System.Threading.Tasks;
using System.Text;
using Newtonsoft.Json.Linq;

namespace Projects
{
    public class Tests
    {
        readonly string url = "http://localhost:4567/projects";
        readonly string projectsPrefix="{\"projects\":[";
        readonly string projectsPostfix="]}";
        readonly string project1="{\"id\":\"1\",\"title\":\"Office Work\",\"completed\":\"false\",\"active\":\"false\",\"description\":\"\",\"tasks\":[{\"id\":\"2\"},{\"id\":\"1\"}]}";

        [SetUp]
        public void Setup()
        {   
        }

        [Test]
        public void GET_Projects_1()
        {
            string response = SendGetRequestAsync(url).Result;
            JObject responseJSON = JObject.Parse(response);

            string expected=projectsPrefix+project1+projectsPostfix;
            JObject expectedJSON = JObject.Parse(expected);

            //Console.WriteLine("AddTodo Test - Response: " + response);
            Assert.That(responseJSON.ToString(), Is.EqualTo(expectedJSON.ToString()));
        }

        [Test]
        public void HEAD_Projects_1()
        {
            string response = SendHeadRequestAsync(url).Result;

            Console.WriteLine("Head: " + response);
            Assert.That(response, Is.Not.EqualTo(""));
        }

        [Test]
        public void AddProject()
        {
            string json=@"
                {
                    ""title"": ""title1"",
                    ""completed"": true,
                    ""active"": true,
                    ""description"": ""Louis' test""
                }";

            var response = SendPostRequestAsync(url, json).Result;
            Console.WriteLine("AddTodo Test - Response: " + response);
            Assert.Pass();
        }


        private static async Task<string> SendPostRequestAsync(string url, string json)
        {
            using var client = new HttpClient();
            var content = new StringContent(json, Encoding.UTF8, "application/json");
            var response = await client.PostAsync(url, content);
            return await response.Content.ReadAsStringAsync();
        }

        private static  async Task<string> SendGetRequestAsync(string url)
        {
            using var client = new HttpClient();
            var response = await client.GetAsync(url);
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