
// mudar conteudo da pagina do home cliente

function mudaCriar(){

    navCriar = document.getElementById('navCriarServico').className;

        // window.alert(navCriar,navSeus);
    if(navCriar != 'ativo'){
         document.getElementById('navCriarServico').className = 'ativo';
         document.getElementById('criarServicos').style.display = 'block';

         document.getElementById('navSeusServico').className = '';
         document.getElementById('seusServicos').style.display = 'none';
    }
}

function mudaSeus(){

    navSeus = document.getElementById('navSeusServico').className;

    // window.alert(navCriar,navSeus);
    if(navSeus != 'ativo'){
         document.getElementById('navSeusServico').className = 'ativo';
         document.getElementById('seusServicos').style.display = 'block';

         document.getElementById('navCriarServico').className = '';
         document.getElementById('criarServicos').style.display = 'none';

    }
}

// navegação da pagina seus serviços

function ativo(){

    navAtivo = document.getElementById('navAtivo').className;

        // window.alert(navCriar,navSeus);
    if(navAtivo != 'ativo'){
         document.getElementById('navAtivo').className = 'ativo';
         document.getElementById('navAvali').className = '';
         document.getElementById('navConta').className = '';
         document.getElementById('navFinal').className = '';
    }
}

function avali(){
    navAvali = document.getElementById('navAvali').className;

        // window.alert(navCriar,navSeus);
    if(navAvali != 'ativo'){
         document.getElementById('navAtivo').className = '';
         document.getElementById('navAvali').className = 'ativo';
         document.getElementById('navConta').className = '';
         document.getElementById('navFinal').className = '';
    }
}
function conta(){
    navConta = document.getElementById('navConta').className;

        // window.alert(navCriar,navSeus);
    if(navConta != 'ativo'){
         document.getElementById('navAvali').className = '';
         document.getElementById('navAtivo').className = '';
         document.getElementById('navConta').className = 'ativo';
         document.getElementById('navFinal').className = '';
    }
}

function final(){
    navFinal = document.getElementById('navFinal').className;

        // window.alert(navCriar,navSeus);
    if(navFinal != 'ativo'){
         document.getElementById('navAvali').className = '';
         document.getElementById('navAtivo').className = '';
         document.getElementById('navConta').className = '';
         document.getElementById('navFinal').className = 'ativo';
    }
}



// $('#mostrar').click(function(e){
//     if ( $('#conteudo').is(':visible')){
//         alert('Eu já estou visível, cara !');
//         return;
//     }
//     $('#conteudo').fadeIn(1000);
// });
//
//
// $('#ocultar').click(function(e){
//     if ( !$('#conteudo').is(':visible')){
//         alert('Eu já estou invisível, cara !');
//         return;
//     }
//     $('#conteudo').fadeOut(1000);
// });
