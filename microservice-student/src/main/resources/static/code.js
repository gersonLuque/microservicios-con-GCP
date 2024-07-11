const baseUrl = "http://localhost:8080/api/student";

      async function fetchStudents() {
        try {
          const response = await fetch(`${baseUrl}/all`);
          console.log(response);
          const students = await response.json();
          displayStudents(students);
        } catch (error) {
          console.error("Error fetching students:", error);
        }
      }

      function displayStudents(students) {
        const studentsBody = document.getElementById("studentsBody");
        studentsBody.innerHTML = "";
        students.forEach((student) => {
          const row = `<tr>
                        <td>${student.id}</td>
                        <td>${student.name}</td>
                        <td>${student.lastName}</td>
                        <td>${student.email}</td>
                        <td>${student.courseId}</td>
                      </tr>`;
          studentsBody.innerHTML += row;
        });
      }

      document.getElementById("downloadForm").addEventListener("submit", function (event) {
        event.preventDefault();
        const fileName = document.getElementById("fileName").value.trim();
        if (!fileName) {
          alert("Please enter a file name.");
          return;
        }

        const downloadUrl = `${baseUrl}/file/${fileName}`;
        window.location.href = downloadUrl;
      });

      fetchStudents();

      async function uploadFile() {

            const fileInput = document.getElementById('fileInput');
            const file = fileInput.files[0];

            console.log(file);

            if (!file) {
                alert("Please select a file.");
                return;
            }

            const formData = new FormData();
            formData.append("file", file);

            try {
                const response = await fetch('http://localhost:8080/api/student/upload', {
                    method: 'POST',
                    body: formData
                });
                console.log(response);

                const message = document.getElementById('message');

                if (response.ok) {
                    const result = await response.text();
                    message.innerText = "File uploaded successfully: " + result;
                } else {
                    message.innerText = "Failed to upload file: " + response.statusText;
                }

            } catch (error) {
                console.error("Error uploading file:", error);
                const message = document.getElementById('message');
                message.innerText = "Error uploading file: " + error;
            }
        }
        document.getElementById('listFilesButton').addEventListener('click', async () => {
            try {
                const response = await fetch('http://localhost:8080/api/student/files');
                if (response.ok) {
                    const fileNames = await response.json();
                    const fileList = document.getElementById('fileList');
                    fileList.innerHTML = '';
                    fileNames.forEach(fileName => {
                        const listItem = document.createElement('li');
                        listItem.textContent = fileName;
                        fileList.appendChild(listItem);
                    });
                } else {
                    alert('Failed to list files');
                }
            } catch (error) {
                console.error('Error fetching file list:', error);
                alert('Error fetching file list');
            }
        })