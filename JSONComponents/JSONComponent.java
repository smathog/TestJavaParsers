package com.Parsers.JSONComponents;

//Interface so JSONCharacter enum can be in the same list...not ideal, but AFAIK Java doesn't have an equivalent
//of the C++ variant library template...
public interface JSONComponent {
    String JSONRepresentation();
}
