using System;
using System.Net.Http;
using System.Text;
using System.Threading.Tasks;

namespace ECSC_ProjectA
{
    public class Tests
    {
        [SetUp]
        public void Setup()
        {
        }

        [Test]
        public void GetTodos()
        {
            string url = "http://localhost:4567";

            string json = "";

            url += "/todos";


            var response = SendGetRequestAsync(url).Result;
            Assert.Pass();
        }

        [Test]
        public void AddTodo()
        {
            string url = "http://localhost:4567";

            string json = "{\"title\": \"my title\",  \"description\": \"vanessa test\"}";

            url += "/todos";


            var response = SendPostRequestAsync(url, json).Result;
            Assert.Pass();
        }


        public  async Task<string> SendPostRequestAsync(string url, string json)
        {
            using var client = new HttpClient();
            var content = new StringContent(json, Encoding.UTF8, "application/json");
            var response = await client.PostAsync(url, content);
            return await response.Content.ReadAsStringAsync();
        }

        public  async Task<string> SendGetRequestAsync(string url)
        {
            using var client = new HttpClient();
            var response = await client.GetAsync(url);
            return await response.Content.ReadAsStringAsync();
        }

    }
}