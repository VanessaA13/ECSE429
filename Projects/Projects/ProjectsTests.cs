using System;
using System.Net.Http;
using System.Text;
using System.Threading.Tasks;
using System.Diagnostics;
using NUnit.Framework;

namespace Projects
{
    public class Tests
    {
        string url = "http://localhost:4567/projects";

        [SetUp]
        public void Setup()
        {
        }

        [Test]
        public void GetProjects()
        {
            var response = SendGetRequestAsync(url).Result;
            Console.WriteLine("AddTodo Test - Response: " + response);
            Assert.Pass();
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


        public async Task<string> SendPostRequestAsync(string url, string json)
        {
            using var client = new HttpClient();
            var content = new StringContent(json, Encoding.UTF8, "application/json");
            var response = await client.PostAsync(url, content);
            return await response.Content.ReadAsStringAsync();
        }

        public async Task<string> SendGetRequestAsync(string url)
        {
            using var client = new HttpClient();
            var response = await client.GetAsync(url);
            return await response.Content.ReadAsStringAsync();
        }

    }
}