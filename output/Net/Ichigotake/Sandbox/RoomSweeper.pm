package Net::Ichigotake::Sandbox::RoomSweeper;

use strict;
use warnings;

sub new {
    my $class = shift;
    my $self = bless {@args}, $class;
    return $self;
}

sub getName {
    my $self = shift;

    return "string"
}

sub sweep {
    my $self = shift;
    my $string = shift;

}


1;
