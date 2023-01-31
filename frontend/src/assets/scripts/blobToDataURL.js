//**blob to dataURL**
export default function blobToDataURL(blob) {
    var a = "data:image/png;base64," + blob
    return a;
}