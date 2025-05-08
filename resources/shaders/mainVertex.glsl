#version 330 core

in vec3 position;
in vec3 color;
in vec2 textureCoord;

out vec3 passColor;
out vec2 passTextureCoord;

uniform mat4 model;

void main(){
    gl_Position = model * vec4(position, 1.0);
    passColor = color;
    passTextureCoord = textureCoord;
}