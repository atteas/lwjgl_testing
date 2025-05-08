#version 330 core

in vec3 passColor;
in vec2 passTextureCoord;

out vec4 outColor;

uniform sampler2D textureSampler;

void main(){
    outColor = texture(textureSampler, passTextureCoord);
}