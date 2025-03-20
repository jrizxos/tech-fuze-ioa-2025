function getLayerPosition(layer) {
    var bounds = layer.bounds;
    var x = bounds[0].as("px");                                         // Left position
    var y = bounds[1].as("px");                                         // Top position
    return x + "," + y;                                                 // Unique key for position
}

function processLayers(layers) {
    for (var i = 0; i < layers.length; i++) {
        var layer = layers[i];
        if (layer.typename === "ArtLayer") {
            var posKey = getLayerPosition(layer);
            if (seenPositions[posKey]) {
                layer.remove(); // Delete duplicate layer
            } else {
                seenPositions[posKey] = true;
                output += "\"hex_" + i + "\":[" + posKey + "],\n";
            }
        }
        else if (layer.typename === "LayerSet") {
            processLayers(layer.layers); // Recursively check groups
        }
    }
}

var doc = app.activeDocument;
var output = "{";
var seenPositions = {}; // Dictionary to store unique positions
processLayers(doc.layers);
output += "}";

// Save to text file
var file = new File("D:\\Desktop\\layer_positions.json");
file.open("w");
file.write(output);
file.close();

alert("Layer positions saved to Desktop as 'layer_positions.txt'");
