# Visual-Cryptography
                    --------------Visual Criptography--------------

    Take two pictures and merge them into one.
    Algorithm behind this program is very simple.Assume that every pixel consists of
    three component as Red,Green,Blue(except alpha).And each component which is color
    object is represented 8 bit.By mean that you can define 255 different value.So every
    pixel can be defined 24-bit for three RGB color.That means 2^24(16 million) different color.
    Without talking so much about theory, Algorithm is based on these bits.The program takes 
    pixels' RGB value from one image and divide every pixel into four part.And distrubute these
    pixels to the another picture's pixels which will encript.Encripted image does not deform.Because
    every 2 bits  ar just replaced with last 2 bits in encripted image.Lets give an example.
    
    Think about we have a RGB value which is RGB(255,255,255).
    White
    RGB (FFFFFF) = RGB(255,255,255) = RGB(11111111,11111111,11111111)
    
    Another four pixels from second(encripted) image is 
    1. Pixel  = RGB(10111011,10101110,10101110)
    2. Pixel  = RGB(10101110,10100010,11011000)
    3. Pixel  = RGB(11001110,10101110,10101110)
    4. Pixel  = RGB(01011110,10101000,11100101)

    now divide first pixel(for each color value) into four part.
        Red             Green          Blue
    11  11  11  11   11 11 11 11    11 11 11 11 

    distrube these values to LSB to every pixel.

    1. Pixel = RGB(101110 11,101011 11,101011 11)
    2. Pixel = RGB(101011 11,101000 11,110110 11)
    3. Pixel = RGB(110011 11,101011 11,101011 11)
    4. Pixel = RGB(010111 11,101010 11,111001 11)

   With this operation, deformation will be minimum level.Because difference
   between  11111111 and 11111100 color value is almost nothing and when you
   think this effect about all picture you can not distinguish that deformation.    
