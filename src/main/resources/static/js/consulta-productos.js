$(document).ready(function () {
    printProductos();
});

function printProductos() {
    $.get('/productos/all/for-table', function (productos) {
        $('#t-productos tbody').empty();
        $.each(productos, function (i, producto) {
            var fila = crearFila();

            appendColContent(fila, producto.nombre);
            appendColContent(fila, producto.stock);
            appendColContent(fila, '$' + producto.precio);
            appendColContent(fila, producto.fechaAlta);
            appendColIconContent(fila, icons.alta, producto, function(producto){
             $.get('/productos/alta/'+ producto.id, function () {
                                 window.location.href='/productos/alta/'+ producto.id;
                             });
            });
            appendColIconContent(fila, icons.baja, producto, function(producto){
             console.log(producto);
            });
            appendColImgContent(fila, producto.imagen);
            appendColIconContent(fila, icons.edit, producto, function (producto) {
                console.log(producto);
            });

            appendColIconContent(fila, icons.delete, producto, function (producto) {
                console.log("borrar", producto);
                $.get('/productos/eliminar/' + producto.id, function (response) {
                    alert(response);
                    printProductos();
                }).fail(function() {
                    alert( "Error al borrar el producto" );
                })
            });
            $('#t-productos tbody').append($(fila));

        })
    })
}

function appendColContent(fila, contenido) {
    var col = crearColumna();
    $(col).append(contenido);
    $(fila).append(col);
}

function appendColImgContent(fila, imagen) {
    var col = crearColumna();
    $(col).append('<img width="100px" src="data:' + imagen.mimeType + ';base64,' + imagen.dataBase64 + '">');
    $(fila).append(col);
}

function appendColIconContent(fila, icono, producto, onclick) {
    var col = crearColumna();
    $(col).append(icono);
    $(fila).append(col);

    $(col).on('click', function () {
        onclick(producto);
    });
}

function crearFila() {
    return $('<tr></tr>');
}

function crearColumna() {
    return $('<td></td>');
}

icons = {
    delete: '<svg class="bi bi-trash-fill" width="2em" height="2em" viewBox="0 0 16 16" fill="currentColor" xmlns="http://www.w3.org/2000/svg">\n' +
        '  <path fill-rule="evenodd" d="M2.5 1a1 1 0 00-1 1v1a1 1 0 001 1H3v9a2 2 0 002 2h6a2 2 0 002-2V4h.5a1 1 0 001-1V2a1 1 0 00-1-1H10a1 1 0 00-1-1H7a1 1 0 00-1 1H2.5zm3 4a.5.5 0 01.5.5v7a.5.5 0 01-1 0v-7a.5.5 0 01.5-.5zM8 5a.5.5 0 01.5.5v7a.5.5 0 01-1 0v-7A.5.5 0 018 5zm3 .5a.5.5 0 00-1 0v7a.5.5 0 001 0v-7z" clip-rule="evenodd"/>\n' +
        '</svg>',
    edit: '<svg class="bi bi-pencil" width="2em" height="2em" viewBox="0 0 16 16" fill="currentColor" xmlns="http://www.w3.org/2000/svg">\n' +
        '  <path fill-rule="evenodd" d="M11.293 1.293a1 1 0 011.414 0l2 2a1 1 0 010 1.414l-9 9a1 1 0 01-.39.242l-3 1a1 1 0 01-1.266-1.265l1-3a1 1 0 01.242-.391l9-9zM12 2l2 2-9 9-3 1 1-3 9-9z" clip-rule="evenodd"/>\n' +
        '  <path fill-rule="evenodd" d="M12.146 6.354l-2.5-2.5.708-.708 2.5 2.5-.707.708zM3 10v.5a.5.5 0 00.5.5H4v.5a.5.5 0 00.5.5H5v.5a.5.5 0 00.5.5H6v-1.5a.5.5 0 00-.5-.5H5v-.5a.5.5 0 00-.5-.5H3z" clip-rule="evenodd"/>\n' +
        '</svg>',
     alta: '<svg class="svg-icon"width="2em" height="2em" viewBox="0 0 20 20">\n'+
           	'<path d="M13.889,11.611c-0.17,0.17-0.443,0.17-0.612,0l-3.189-3.187l-3.363,3.36c-0.171,0.171-0.441,0.171-0.612,0c-0.172-0.169-0.172-0.443,0-0.611l3.667-3.669c0.17-0.17,0.445-0.172,0.614,0l3.496,3.493C14.058,11.167,14.061,11.443,13.889,11.611 M18.25,10c0,4.558-3.693,8.25-8.25,8.25c-4.557,0-8.25-3.692-8.25-8.25c0-4.557,3.693-8.25,8.25-8.25C14.557,1.75,18.25,5.443,18.25,10 M17.383,10c0-4.07-3.312-7.382-7.383-7.382S2.618,5.93,2.618,10S5.93,17.381,10,17.381S17.383,14.07,17.383,10"/>\n'+
           	'</svg>',
     baja: '<svg class="svg-icon" width="2em" height="2em" viewBox="0 0 20 20">\n'+
           	'<path d="M13.962,8.885l-3.736,3.739c-0.086,0.086-0.201,0.13-0.314,0.13S9.686,12.71,9.6,12.624l-3.562-3.56C5.863,8.892,5.863,8.611,6.036,8.438c0.175-0.173,0.454-0.173,0.626,0l3.25,3.247l3.426-3.424c0.173-0.172,0.451-0.172,0.624,0C14.137,8.434,14.137,8.712,13.962,8.885 M18.406,10c0,4.644-3.763,8.406-8.406,8.406S1.594,14.644,1.594,10S5.356,1.594,10,1.594S18.406,5.356,18.406,10 M17.521,10c0-4.148-3.373-7.521-7.521-7.521c-4.148,0-7.521,3.374-7.521,7.521c0,4.147,3.374,7.521,7.521,7.521C14.148,17.521,17.521,14.147,17.521,10"/>\n'+
           	 '</svg>'
}