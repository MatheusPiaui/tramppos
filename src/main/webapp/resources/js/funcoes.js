
function cadastro(id)
{
  var clicou = "btn_" + id;

  if(clicou == "btn_cliente")
  {
    document.getElementById("btn_cliente").className = "ativado";
    document.getElementById("btn_profissional").className = "";
    document.getElementById("cadastro_cliente").style.display = "block";
    document.getElementById("cadastro_profissional").style.display = "none";
  }
  else
  {
    document.getElementById("btn_profissional").className = "ativado";
    document.getElementById("btn_cliente").className = "";
    document.getElementById("cadastro_cliente").style.display = "none";
    document.getElementById("cadastro_profissional").style.display = "block";
  }

}
