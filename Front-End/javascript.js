

async function login(){
    userName = document.getElementById("userName").value;
    password = document.getElementById("password").value;


    let url = 'http://localhost:8080/P1-Backend/verification?userName=' + userName + '&password=' + password;
    let response = await fetch(url, {method: 'GET', headers:{'Content-Type': 'application/json; charset=utf-8'}});

    if(response.status == 200){
        let user = await response.json();
        if(localStorage == null){
            document.getElementById("login").addEventListener("click", function(){
                window.localStorage.setItem('userStatus', user.roleType);
            });
        }
        else{
            localStorage.getItem('userId');
            localStorage.getItem('userName');
            localStorage.setItem('userId', user.userId);
            localStorage.setItem('userStatus', user.roleType);
        }
        if(user.roleType == 'Administrator'){
            window.location = './adminDashboard.html';
        }
        else{
            user.roleType = 'Employee';
            window.location = './EmpEmployeeTicketing.html';
        }

    }
}

//function to handle employee registration
async function signupEmp(){

    let user = {
        userName: document.getElementById("userName").value,
        email: document.getElementById("email").value,
        password: document.getElementById("password").value,
        roleType: document.getElementById("roleEmp").value
    }
    
    let url = 'http://localhost:8080/P1-Backend/users';
    
    let response = await fetch(url, {
        method: 'POST',
        headers: {
            'ContentType' : 'Application/json; charset= UTF-8'
        },
        body: JSON.stringify(user)
    });


    
    if(response.status == 200){
        if(localStorage.getItem == null){
            document.getElementById("login").addEventListener("click", function(){
                window.localStorage.setItem('userId', user.userId);
            });
        }
        else{
            localStorage.getItem('userId');
            localStorage.setItem('userId', user.userId);
        }
        window.location = './login.html';
    }
    else{
        alert("Something happened");
    }
    }


//function to handle admin registration
async function signupAdmin(){

    let user = {
        userName: document.getElementById("userName").value,
        email: document.getElementById("email").value,
        password: document.getElementById("password").value,
        roleType: document.getElementById("roleAdmin").value
    }
    
    let url = 'http://localhost:8080/P1-Backend/users';
    
    let response = await fetch(url, {
        method: 'POST',
        headers: {
            'ContentType' : 'Application/json; charset= UTF-8'
        },
        body: JSON.stringify(user)
    });


    
    if(response.status == 200){
        if(localStorage.getItem == null){
            document.getElementById("login").addEventListener("click", function(){
                window.localStorage.setItem('userId', user.userId);
            });
        }
        else{
            localStorage.getItem('userId');
            localStorage.setItem('userId', user.userId);
        }
        window.location = './login.html';
    }
    else{
        alert("Something happened");
    }
    }

function backToHome(){
    let clicked = true;
    document.getElementById('cancelBtn').addEventListener("click", function(){
        if(clicked == true){
            window.location = './Index.html';
        }
    });
}

async function createReimbursement(){
   
    let reimbursement = {
        reimbursementType: document.getElementById('reimbursementType').value,
        reimbursementCost: document.getElementById('reimbursementCost').value,
        reimbursementStatus: document.getElementById('reimbursementStatus').value,
        userId: window.localStorage.getItem('userId')
    }
let user = window.localStorage.getItem('userId', reimbursement.userId);


    let url = 'http://localhost:8080/P1-Backend/reimbursements?user-id=' + user;


    
    let response = await fetch(url, {
        method: 'POST',
        headers: {
            'content-Type' : 'Application/json; charset=UTF-8'
        },
        body: JSON.stringify(reimbursement)
    });    
        if(response){
            alert('Success!');
            window.location.reload(true);
        }
    }
   
    async function editReimbursements(){
        
   
        let reimbursement = {
            reimbursementType: document.getElementById('reimbursementType').value,
            reimbursementCost: document.getElementById('reimbursementCost').value,
            reimbursementStatus: document.getElementById('reimbursementStatus').value,
            userId: window.localStorage.getItem('userId'),
            reimbursementId: document.getElementById('reimbursementId').value
        }


        
        let userId = window.localStorage.getItem('userId');
        let url = 'http://localhost:8080/P1-Backend/reimbursements?reimbursement-id=' + reimbursement.reimbursementId + '&user-id=' + userId;
        
        let response = await fetch(url, {
            method: 'PUT',
            headers: {
                'contentType' : 'Application/json; charset=UTF-8'
            },
            body: JSON.stringify(reimbursement)
        });


        if(response.status == 200){
    
            window.location.reload();
          
        }
        else{
            alert("Something happened");
        }
        }
        async function editReimbursementsAdmin(){
        
   
            let reimbursement = {
                reimbursementType: document.getElementById('reimbursementType').value,
                reimbursementCost: document.getElementById('reimbursementCost').value,
                reimbursementStatus: document.getElementById('reimbursementStatus').value,
                userId: document.getElementById('userId').value,
                reimbursementId: document.getElementById('reimbursementId').value
            }
    
    
            
            let userId = reimbursement.userId;
            let url = 'http://localhost:8080/P1-Backend/reimbursements?reimbursement-id=' + reimbursement.reimbursementId + '&user-id=' + userId;
            
            let response = await fetch(url, {
                method: 'PUT',
                headers: {
                    'contentType' : 'Application/json; charset=UTF-8'
                },
                body: JSON.stringify(reimbursement)
            });
    
    
            if(response.status == 200){
        
                window.location.reload();
              
            }
            else{
                alert("Something happened");
            }
            }

    async function delEmp(){
        let userId = document.getElementById("userId").value;
        let url = 'http://localhost:8080/P1-Backend/users?user-id=' + userId;

        let response = await fetch(url, {
            method: 'DELETE', headers:{
                'content-type': 'application/json; charset=utf-8'
            }
        });

        if(response == 200){
            document.location.reload();
        }
        else{
        }

    }

    async function getEmpSingle(){
        let userId = document.getElementById("empNum").value;
        let url = 'http://localhost:8080/P1-Backend/users?user-id=' + userId;

        let response = await fetch(url, {
            method: 'GET',
            headers:{
                'Content-Type': 'application/json; charset=utf-8'
            }
        });

        let user = await response.json()
        let htmlElement = document.getElementById("empSingle");
        
        if(user.userName != null){
            htmlElement.innerHTML += "<p>UserIdNumber: " + user.userId + "</p>";
            htmlElement.innerHTML += "<p>Username: " + user.userName + "</p>";
            htmlElement.innerHTML += "<p>Email: " + user.email + "</p>";   
            htmlElement.innerHTML += "<p>Password: " + user.password + "</p>";
        }else{
            alert("Employee Does Not Live Here!")
        }
        
        
    }

    async function clrList() {
        
        window.location.reload(true);
    }

    async function getAllEmp(){

        let url = 'http://localhost:8080/P1-Backend/users';

        let response = await fetch(url, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json; charset=utf-8'
            }
        });
            
            let array = await response.json();
            let htmlElement = document.getElementById('empAll');

            for(let i = 0; i < array.length; i++){
                let user = array[i];
                htmlElement.innerHTML += "<p>UserIdNumber: " + user.userId + "</p>";
                htmlElement.innerHTML += "<p>Username: " + user.userName + "</p>";
                htmlElement.innerHTML += "<p>Email: " + user.email + "</p>";   
                htmlElement.innerHTML += "<p>Password: " + user.password + "</p>";
            }
    }




    async function filterAllReimbursements() {

        let userId = window.localStorage.getItem('userId');
        let url = 'http://localhost:8080/P1-Backend/reimbursements?user-id=' + userId;

        let response = await fetch(url, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json; charset=utf-8'
            }
        });
 
            let array = await response.json();
            let htmlElement = document.getElementById('List');

            for(let i = 0; i < array.length; i++){
                let reimbursementList = array[i];
                
                htmlElement.innerHTML += "<li><a id= 'reimbursementId'> Reimbursement Id Number: "  + reimbursementList.reimbursementId + "<br>" +
                 "user Id: " + reimbursementList.userId + "<br>" +
                 "Reimbursement Type: " + reimbursementList.reimbursementType + "<br>" +
                 "Reimburesement Cost: " + reimbursementList.reimbursementCost + "<br>" +
                 "Reimbursement Status: " + reimbursementList.reimbursementStatus + "</a></li><br>";
            }
            
    };

    async function filterAllReimbursementsAdmin() {

        let userId = window.localStorage.getItem('userId');
        let url = 'http://localhost:8080/P1-Backend/reimbursements';

        let response = await fetch(url, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json; charset=utf-8'
            }
        });
 
            let array = await response.json();
            let htmlElement = document.getElementById('List');

            for(let i = 0; i < array.length; i++){
                let reimbursementList = array[i];
                
                htmlElement.innerHTML += "<li><a id= 'reimbursementId'> Reimbursement Id Number: "  + reimbursementList.reimbursementId + "<br>" +
                 "user Id: " + reimbursementList.userId + "<br>" +
                 "Reimbursement Type: " + reimbursementList.reimbursementType + "<br>" +
                 "Reimburesement Cost: " + reimbursementList.reimbursementCost + "<br>" +
                 "Reimbursement Status: " + reimbursementList.reimbursementStatus + "</a></li><br>";
            }
            
    };

    async function getAllReimbursements() {

        let userId = window.localStorage.getItem('userId');
        let url = 'http://localhost:8080/P1-Backend/reimbursements?user-id=' + userId;

        let response = await fetch(url, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json; charset=utf-8'
            }
        });
 
            let array = await response.json();
            let htmlElement = document.getElementById('List');

            for(let i = 0; i < array.length; i++){
                let reimbursementList = array[i];
                htmlElement.innerHTML += "<div id= 'RList'>"
                htmlElement.innerHTML += "<p id= 'reimbursementId'> Reimbursement Id Number: " + reimbursementList.reimbursementId + "</p>";
                htmlElement.innerHTML += "<p id= 'userId'> user Id: " + reimbursementList.userId + "</p></li>";
                htmlElement.innerHTML += "<p id= 'reimbursementType'> Reimbursement Type: " + reimbursementList.reimbursementType + "</p>";
                htmlElement.innerHTML += "<p id= 'reimbursementCost'> Reimburesement Cost: " + reimbursementList.reimbursementCost + "</p>";
                htmlElement.innerHTML += "<p id= 'reimbursementStatus'> Reimbursement Status: " + reimbursementList.reimbursementStatus + "</p>";
                htmlElement.innerHTML += "</div>"
            }
            
    };

    async function getAllReimbursementsAdmin() {

        let userId = window.localStorage.getItem('userId');
        let url = 'http://localhost:8080/P1-Backend/reimbursements';

        let response = await fetch(url, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json; charset=utf-8'
            }
        });
 
            let array = await response.json();
            let htmlElement = document.getElementById('List');

            for(let i = 0; i < array.length; i++){
                let reimbursementList = array[i];
                htmlElement.innerHTML += "<div id= 'RList'>"
                htmlElement.innerHTML += "<p id= 'reimbursementId'> Reimbursement Id Number: " + reimbursementList.reimbursementId + "</p>";
                htmlElement.innerHTML += "<p id= 'userId'> user Id: " + reimbursementList.userId + "</p></li>";
                htmlElement.innerHTML += "<p id= 'reimbursementType'> Reimbursement Type: " + reimbursementList.reimbursementType + "</p>";
                htmlElement.innerHTML += "<p id= 'reimbursementCost'> Reimburesement Cost: " + reimbursementList.reimbursementCost + "</p>";
                htmlElement.innerHTML += "<p id= 'reimbursementStatus'> Reimbursement Status: " + reimbursementList.reimbursementStatus + "</p>";
                htmlElement.innerHTML += "</div>"
            }
            
    };
   


    async function showReimbursementsEmp(){

        let htmlElement = document.getElementById('ListEdit');

            htmlElement.innerHTML += "<input id='reimbursementId' type= 'number' placeholder= 'Enter the Reimbursement Id'> "
            htmlElement.innerHTML += "<label for='reimbursementType'>Choose an reimbursement Type:</label>"
            htmlElement.innerHTML+= "<select name='reimbursementTypeDrop' id='reimbursementType'><option value='default'>Choose:</option><option id='reimbursementType' value='food'>Food</option> <option id='reimbursementType' value='travel'>Travel</option><option id='reimbursementType' value='lodging'>Lodging</option></select>"
            htmlElement.innerHTML+= "<br><label for='reimbursementCost'>reimbursement Cost:</label>"
            htmlElement.innerHTML+= "<input type='number' id='reimbursementCost' name='reimbursementCost' step='.01' min='0' max='10' placeholder='$0.00'>"
            htmlElement.innerHTML += "<select name= 'reimbursementStatusDrop' id='reimbursementStatus'><option value= 'Pending' default>Pending</option><option value='Cancel'>Cancel</option></select>"
            htmlElement.innerHTML += "<button type= 'submit' onclick= 'editReimbursements()'>Submit Changes</button>"
            
        }


        async function showReimbursementsAdmin(){

            let htmlElement = document.getElementById('ListEdit');
            
                htmlElement.innerHTML += "<input id='reimbursementId' type= 'number' placeholder= 'Reimbursement Id'> "
                htmlElement.innerHTML += "<input type= 'number' placeholder= 'Employee's Id' id='userId'>"
                htmlElement.innerHTML += "<label for='reimbursementType'>Choose an reimbursement Type:</label>"
                htmlElement.innerHTML += "<select name='reimbursementTypeDrop' id='reimbursementType'><option value='default'>Choose:</option><option value='food'>Food</option> <option value='travel'>Travel</option><option value='lodging'>Lodging</option></select>"
                htmlElement.innerHTML += "<select name='reimbursementStatusDrop' id='reimbursementStatus'><option value='default'>Choose:</option><option value='Accepted'>Accept</option> <option value='Reject'>Reject</option></select>"
                htmlElement.innerHTML += "<br><label for='reimbursementCost'>reimbursement Cost:</label>"
                htmlElement.innerHTML += "<input type='number' id='reimbursementCost' name='reimbursementCost' step='.01' min='0' max='10' placeholder='$0.00'>"
                htmlElement.innerHTML += "<button type= 'submit' onclick= 'editReimbursementsAdmin()'>Submit Changes</button>"
                
            }
        
            function myFunction(){


                var input, filter, ul, i, li, a, txtValue;
                input = document.getElementById('search');
                
                filter = input.value.toUpperCase();
                ul = document.getElementById("List");
                li = ul.getElementsByTagName('li');

                for(i = 0; 0 < li.length; i++){

                    a = li[i].getElementsByTagName("a")[0];

                    txtValue = a.textContent || a.innerText;

                    if(txtValue.toUpperCase().indexOf(filter) > -1){
                        li[i].style.display = "";
                    }
                    else{
                        li[i].style.display = "none"
                    }
                }
            }
            

    async function logout(){
        window.localStorage.clear();
        window.location = './Index.html'
    }
