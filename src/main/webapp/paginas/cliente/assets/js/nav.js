
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
