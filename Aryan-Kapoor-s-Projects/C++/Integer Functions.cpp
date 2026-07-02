# include <iostream>
# include <cassert>

void pattern(unsigned int n) {

if(n == 0) {
    std::cout << "*" << std::endl;
}
else {
//top half
for(unsigned int i{n}; i >= 1 ;--i) { 

    for (unsigned int j{0}; j < (n-i) ; ++j)
    {
        std::cout << " ";
    }
    
    for (unsigned int k{0}; k < (2*i+1) ; ++k) {

        std::cout << "*";
     }
    std::cout << "" << std::endl;
    }
    
    for (unsigned int spaces = 0; spaces < n; ++spaces) {
        std::cout << " ";
    }
    std::cout << "*" <<std::endl;
for(unsigned int i{1}; i <= n ;++i) {
    for(unsigned int j{1}; j < (n-i+1); ++j) {
        std::cout << " ";
    }

    for(unsigned int k{0}; k < (2*i+1);++k) {
        std::cout << "*";
    }
    std::cout<< "" <<std::endl;
}
    
}
return;
}

unsigned int log10(unsigned int n) {

    assert (n != 0);

    unsigned int m{0};

    while(n >= 10) {
        n = n/10;
        ++m;
    }

    return m;
}

unsigned int count(unsigned int n, unsigned int bit) {

    assert((bit == 0) || (bit == 1));
    unsigned int bit_count{0};

    for(unsigned int k{1u << 31}; k > 0; k >>=1 ) {
        if( (n & k) && (bit == 1) ) {
            ++bit_count;
        }
        else if ((bit == 0) && ((n & k) == 0)) 
        {
            ++bit_count;
        }
    }

    return bit_count;
}

unsigned int swap_bytes(unsigned int n, unsigned int b0, unsigned int b1) {
    
    assert(((b0 <= 3) && (0 <= b0)) && ((b1 <= 3 )&&(0 <= b1)));

    if(b0 == b1) {
        return n;
    }

    unsigned int byte0 = (n >> (8*b0)) & 0xFF;
    unsigned int byte1 = (n >> (8*b1)) & 0xFF;
    
    n &= ~(0xFF << (8*b0));
    n &= ~(0xFF << (8*b1));

    n |= (byte0 << (8*b1));
    n |= (byte1 << (8*b0));


    return n;

}

int main() {
int n;
std::cout << "Enter an positive integer: " << std::endl;
std::cin >> n;

int m;
std::cout << "Enter an positive integer: " << std::endl;
std::cin >> m;

pattern(n);

std::cout << "log_10 (" << m << ") = " << log10(m) << std::endl;

std::cout << count(56,0) << std::endl;

std::cout << swap_bytes(255,1,0);
return 0;
}
