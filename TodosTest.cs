using System;
using System.Data;
using System.Dynamic;
using System.Net.Http;
using System.Text;
using System.Text.RegularExpressions;
using System.Threading.Tasks;

namespace _429Project
{
    public class Tests
    {
        [SetUp]
        public void Setup()
        {
        }

        /* 
        test for get all todo instances 
        author: Mike
        */
        [Test]
        public void GetTodos()
        {
            string url = "http://localhost:4567";
            string todo1 = "{\"id\":\"1\",\"title\":\"scan paperwork\",\"doneStatus\":\"false\",\"description\":\"\"";
            string todo2 = "{\"id\":\"2\",\"title\":\"file paperwork\",\"doneStatus\":\"false\",\"description\":\"\"";    
            string json = "";

            url += "/todos";

            var response = SendGetRequestAsync(url).Result;
            Assert.IsTrue(response.Contains(todo1));
            Assert.IsTrue(response.Contains(todo2));
        }

        /* 
        test for get headers of all todo instances 
        author: Mike
        */
        [Test]
        public void GetHeadersTodos()
        {
            string url = "http://localhost:4567";
            string json = "";
            url += "/todos";
            var response = SendHeadRequestAsync(url).Result;
            Assert.False(String.IsNullOrEmpty(response));
            Assert.IsTrue(response.Contains("application/json"));
            Assert.IsTrue(response.Contains("chunked"));
            Assert.IsTrue(response.Contains("Jetty(9.4.z-SNAPSHOT)"));

        }

        /* 
        test for post a todo instance 
        author: Mike
        */
        [Test]
        public void AddTodo()
        {
            string url = "http://localhost:4567";

            string json = "{\"title\": \"my title\",  \"description\": \"test\"}";
            string todo3 = "{\"id\":\"3\",\"title\":\"my title\",\"doneStatus\":\"false\",\"description\":\"test\"}";

            url += "/todos";

            var response = SendPostRequestAsync(url, json).Result;
            var todos = SendGetRequestAsync(url).Result;
            Assert.IsTrue(todos.ToString().Contains(todo3));
        
        }

        /* 
        test for get a todo instance with id 
        author: Mike
        */
        [Test]
        public void GetTodoWithId()
        {
            string url = "http://localhost:4567";
            string todo1 = "{\"id\":\"1\",\"title\":\"scan paperwork\",\"doneStatus\":\"false\",\"description\":\"\"";
            string json = "";

            url += "/todos/1";

            var response = SendGetRequestAsync(url).Result;

            Assert.IsTrue(response.Contains(todo1));
        }

        /* 
        test for get headers of all todo instances 
        author: Mike
        */
        [Test]
        public void GetHeadersTodoWithId()
        {
            string url = "http://localhost:4567";
            string json = "";
            url += "/todos/1";
            var response = SendHeadRequestAsync(url).Result;
            Assert.False(String.IsNullOrEmpty(response));
            Assert.IsTrue(response.Contains("application/json"));
            Assert.IsTrue(response.Contains("chunked"));
            Assert.IsTrue(response.Contains("Jetty(9.4.z-SNAPSHOT)"));
        }

        /* 
        test for post a todo instance or amend an existing 
        author: Mike
        */
        [Test]
        public async Task ChangeTodoWithId()
        {
            string url = "http://localhost:4567/todos/1";
            string todo1 = "{\"id\":\"1\",\"title\":\"scan paperwork\",\"doneStatus\":\"true\",\"description\":\"test\"";
            string json = "{\"title\": \"scan paperwork\", \"doneStatus\": true,  \"description\": \"test\"}";

            string response = await SendPostRequestAsync(url, json);
            Assert.IsTrue(response.Contains(todo1));
        }

        /* 
        test for put a todo instance or amend an existing 
        author: Mike
        */
        [Test]
        public async Task PutChangeTodoWithId()
        {
            string url = "http://localhost:4567/todos/1";
            string todo1 = "{\"id\":\"1\",\"title\":\"scan paperwork\",\"doneStatus\":\"true\",\"description\":\"put test\"";
            string json = "{\"title\": \"scan paperwork\", \"doneStatus\": true,  \"description\": \"put test\"}";
            string response = await SendPostRequestAsync(url, json);
            Assert.IsTrue(response.Contains(todo1));
        }

        /* 
        test for delete a todo instance
        author: Mike
        */
        [Test]
        public async Task DeleteTodoWithId()
        {
            string url = "http://localhost:4567/todos/3";
            string todo1 = "{\"id\":\"2\",\"title\":\"file paperwork\",\"doneStatus\":\"false\",\"description\":\"\"}";
            string json = "";
            string response = await SendPostRequestAsync(url, json);
            Assert.False(response.Contains(todo1));
        }


        /* 
        test for get projects in relationship with a todo with a todo id 
        author: Mike
        */
        [Test]
        public void GetTodotasksofWithid() 
    
        {
            string url = "http://localhost:4567";
            string project1 = "{\"id\":\"1\",\"title\":\"Office Work\",\"completed\":\"false\",\"active\":\"false\",\"description\":\"\"";
            string json = "";

            url += "/todos/1/tasksof";

            var response = SendGetRequestAsync(url).Result;
            Assert.True(response.Contains(project1));
        
        }

        /* 
        test for get headers of projects in relationship with a todo with a todo id 
        author: Mike
        */
        [Test]
        public void GetHeadersTodotasksofWithid() 
        {
            string url = "http://localhost:4567";
            string json = "";
            url += "/todos/1/tasksof";
            var response = SendHeadRequestAsync(url).Result;
            Assert.False(String.IsNullOrEmpty(response));

        }

        /* 
        test for add a todo instance relationship with a project with a todo id 
        author: Mike
        */
        [Test]
        public void TestAddTodotasksofWithid()
        {
            string url = "http://localhost:4567";
            string project1 = "{\"id\":\"1\",\"title\":\"Office Work\",\"completed\":\"false\",\"active\":\"false\",\"description\":\"\"";
            string json = "{\"id\": \"1\"}";
            url += "/todos/3/tasksof";
            var rs = SendPostRequestAsync(url, json);
            var response = SendGetRequestAsync(url).Result;
            Assert.True(response.Contains(project1));
         
        }
        
        /* 
        test for delete the relationship between a todo with its id and a project with its id 
        author: Mike
        */
        [Test]
        public void TestDeletetasksofWith2Id()
        {
            string url = "http://localhost:4567";
            string project1 = "{\"id\":\"1\",\"title\":\"Office Work\",\"completed\":\"false\",\"active\":\"false\",\"description\":\"\"}";
            string json = "";
            url += "/todos/3/tasksof/1";
            var rs = SendDeleteRequestAsync(url);
            var response = SendGetRequestAsync("http://localhost:4567/todos/3/tasksof").Result;
            Assert.False(response.Contains(project1));

        }

        public  async Task<string> SendPostRequestAsync(string url, string json)
        {
            using var client = new HttpClient();
            var content = new StringContent(json, Encoding.UTF8, "application/json");
            var response = await client.PostAsync(url, content);
            return await response.Content.ReadAsStringAsync();
        }

        public  async Task<string> SendPutRequestAsync(string url, string json)
        {
        using var client = new HttpClient();
        var content = new StringContent(json, Encoding.UTF8, "application/json");
        var response = await client.PutAsync(url, content);
        return await response.Content.ReadAsStringAsync();
        }
        public  async Task<string> SendGetRequestAsync(string url)
        {
            using var client = new HttpClient();
            var response = await client.GetAsync(url);
            return await response.Content.ReadAsStringAsync();
        }

        public  async Task<string> SendDeleteRequestAsync(string url)
        {
            using var client = new HttpClient();
            var response = await client.DeleteAsync(url);
            return await response.Content.ReadAsStringAsync();
        }

        public async Task<string> SendHeadRequestAsync(string url)
        {
            using var client = new HttpClient();
            var response = await client.SendAsync(new HttpRequestMessage
            {
                Method = HttpMethod.Head,
                RequestUri = new Uri(url)
            });
            await response.Content.ReadAsStringAsync();

            if (response.IsSuccessStatusCode){
                return response.ToString();
            } else {
                return "";
            }
        }

    }
}