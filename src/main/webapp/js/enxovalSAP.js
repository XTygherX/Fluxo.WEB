


function inserirLinhaTabela(string) {
    // Captura a referência da tabela com id “minhaTabela”
    if (string == 'rede'){
        var table = document.getElementById("minhaTabelaSAPREDE");
    }else if (string == 'adm'){
        var table = document.getElementById("minhaTabelaSAPPDV");
    }else if (string == 'pdv'){
        var table = document.getElementById("minhaTabelaSAPADM");
    }else if (string == 'loja'){
        var table = document.getElementById("minhaTabelaSAPLOJA");
    }

    // Captura a quantidade de linhas já existentes na tabela
    var numOfRows = table.rows.length;
    // Captura a quantidade de colunas da última linha da tabela
    var numOfCols = table.rows[numOfRows-1].cells.length;
    decisaoLoop(table, numOfRows, numOfCols,string);

}
function decisaoLoop(table, numOfRows, numOfCols,string) {
    // Faz um loop para criar as colunas
    if (string == 'rede'){
        loopInserirValores(table, numOfRows, numOfCols,string)
    }else if (string == 'adm'){
        loopInserirValores(table, numOfRows, numOfCols,string)
    }else if (string == 'pdv'){
        loopInserirValores(table, numOfRows, numOfCols,string)
    }else if (string == 'loja'){
        loopInserirValores(table, numOfRows, numOfCols,string)
    }

}

function loopInserirValores(table, numOfRows, numOfCols,string){
    for (var j = 1; j < 10; j++) {
        // Insere uma linha no fim da tabela.
        var newRow = table.insertRow(numOfRows);
        newCell = newRow.insertCell(0);
        newCell.innerHTML = "300m";
        newCell = newRow.insertCell(0);
        newCell.innerHTML = string+j;
        newCell = newRow.insertCell(0);
        newCell.innerHTML = "00"+j;
        }
}